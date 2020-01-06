package com.bawei6.usermodule.view.activity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.baselibrary.utils.AliyunUtils;
import com.bawei6.baselibrary.utils.ThreadUtils;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.view.adapter.MyChatRecylerAdapter;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.ilike.voicerecorder.widget.VoiceRecorderView;

import org.jivesoftware.smack.chat2.Chat;


public class ChatActivity extends AppCompatActivity {

    private TextView text_toUsername;
    private ImageView chat_image_back;
    private ImageView chat_image_phone;
    private ImageView chat_image_more;
    private Button chat_btn_send;
    private ImageView image_sendVoice;
    private ImageView image_sendImage;
    private ImageView image_sendCupture;
    private ImageView image_sendLocation;
    private ImageView image_sendFace;
    private ImageView image_more;
    private RecyclerView chat_recyclerView;
    private EditText chat_edit_sendMessage;
    private String tousername;
    private XmppManager mXmppManager;
    private String sendMessage;
    private MyChatRecylerAdapter myChatRecylerAdapter;
    private Chat chat;
    private VoiceRecorderView ease_voice;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        initEvent();
        initListener();
        tousername = getIntent().getStringExtra("tousername");
        if (tousername != null) {
            text_toUsername.setText(tousername);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initEvent() {
        //发送文本消息
        chat_btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
                ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
                    @Override
                    public void run() {
                        XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(chat, sendMessage);
                    }
                });
                refreshAdapter(sendMessage, MsgEntity.MsgType.Txt);
                chat_edit_sendMessage.setText("");
            }
        });


        //发送语音
        image_sendVoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return ease_voice.onPressToSpeakBtnTouch(view, motionEvent, new VoiceRecorderView.EaseVoiceRecorderCallback() {
                    @Override
                    public void onVoiceRecordComplete(final String voiceFilePath, int voiceTimeLength) {
                        final String fileName = createFileName();
                        AliyunUtils.getInstance().upload(BaseConstant.ALI_BUCKETNAME, "audio/" + fileName+".mp3", voiceFilePath, new OSSCompletedCallback() {

                            @Override
                            public void onSuccess(OSSRequest request, OSSResult result) {
                                XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(chat, BaseConstant.ALI_FILE_PATH + "audio/" + fileName + ".mp3");
                            }

                            @Override
                            public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {

                            }
                        });
                        refreshAdapter(voiceFilePath, MsgEntity.MsgType.Audio);
                    }
                });
            }
        });

        image_sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertView("上传头像", null, "取消", null,
                        new String[]{"拍照", "从相册中选择"},
                        ChatActivity.this, AlertView.Style.ActionSheet, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        switch (position) {
                            /**
                             * 拍照
                             */
                            case 0:
                                Intent intent1 = new Intent();
                                intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                                imagePath = "/sdcard/DCIM/Camera/userheader.jpg";
                                ContentResolver contentResolver = getContentResolver();
                                ContentValues values = new ContentValues();
                                values.put(MediaStore.Images.Media.DATA, imagePath);
                                Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                intent1.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                startActivityForResult(intent1, 101);
                                break;
                            /**
                             * 相册
                             */
                            case 1:
                                Intent intent2 = new Intent();
                                intent2.setAction(Intent.ACTION_PICK);
                                intent2.setType("image/*");
                                startActivityForResult(intent2, 102);
                                break;
                            default:
                        }
                    }
                }).setCancelable(true).show();
            }
        });
    }

    private void refreshAdapter(String message, MsgEntity.MsgType msgType) {
        myChatRecylerAdapter.setMsgEntities(new MsgEntity(tousername, BaseConstant.userName, message, msgType));
    }

    private String createFileName() {
        return BaseConstant.userCode + "_" + System.currentTimeMillis();
    }


    private void initView() {
        text_toUsername = (TextView) findViewById(R.id.text_toUsername);
        chat_image_back = (ImageView) findViewById(R.id.chat_image_back);
        chat_image_phone = (ImageView) findViewById(R.id.chat_image_phone);
        chat_image_more = (ImageView) findViewById(R.id.chat_image_more);
        chat_btn_send = (Button) findViewById(R.id.chat_btn_send);
        image_sendVoice = (ImageView) findViewById(R.id.image_sendVoice);
        image_sendImage = (ImageView) findViewById(R.id.image_sendImage);
        image_sendCupture = (ImageView) findViewById(R.id.image_sendCupture);
        image_sendLocation = (ImageView) findViewById(R.id.image_sendLocation);
        image_sendFace = (ImageView) findViewById(R.id.image_sendFace);
        image_more = (ImageView) findViewById(R.id.image_more);
        chat_recyclerView = (RecyclerView) findViewById(R.id.chat_recyclerView);
        chat_edit_sendMessage = (EditText) findViewById(R.id.chat_edit_sendMessage);
        ease_voice = (VoiceRecorderView) findViewById(R.id.ease_voice);

        chat_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myChatRecylerAdapter = new MyChatRecylerAdapter(this);
        chat_recyclerView.setAdapter(myChatRecylerAdapter);


    }

    private void submit() {
        // validate
        sendMessage = chat_edit_sendMessage.getText().toString().trim();
        if (TextUtils.isEmpty(sendMessage)) {
            Toast.makeText(this, "sendMessage不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    private void initListener() {
        ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                chat = XmppManager.getInstance().getXmppMsgManager().getFriendChat(tousername + "@" + XmppManager.getInstance().getXmppConfig().getDomainName());
                XmppManager.getInstance().addMessageListener(new IMsgCallback() {
                    @Override
                    public void onSuccess(final MsgEntity msgEntity) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myChatRecylerAdapter.setMsgEntities(msgEntity);
                            }
                        });
                    }

                    @Override
                    public void onFailed(Throwable throwable) {

                    }
                });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //图片发送
        if (requestCode == 102 && resultCode == RESULT_OK) {
            String localPath = null;
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(), proj, null, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                localPath = cursor.getString(column_index);
            }
            cursor.close();
            final String fileName = createFileName();
            AliyunUtils.getInstance().upload(BaseConstant.ALI_BUCKETNAME, "video/" + fileName+".png", localPath, new OSSCompletedCallback() {
                @Override
                public void onSuccess(OSSRequest request, OSSResult result) {
                    XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(chat, BaseConstant.ALI_FILE_PATH + "video/" + fileName + ".png");
                }

                @Override
                public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {

                }
            });
            refreshAdapter(localPath, MsgEntity.MsgType.Video);
        }else if (requestCode == 101 && resultCode == RESULT_OK) {
            final String fileName = createFileName();
            AliyunUtils.getInstance().upload(BaseConstant.ALI_BUCKETNAME, "video/" + fileName+".png", imagePath, new OSSCompletedCallback() {
                @Override
                public void onSuccess(OSSRequest request, OSSResult result) {
                    XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(chat, BaseConstant.ALI_FILE_PATH + "video/" + fileName + ".png");
                }

                @Override
                public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {

                }
            });
            refreshAdapter(imagePath, MsgEntity.MsgType.Video);
        }

    }
}
