package com.bawei6.usermodule.view.activity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.bawei6.baselibrary.base.BaseActivity;
import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.baselibrary.utils.AliyunUtils;
import com.bawei6.baselibrary.utils.ThreadUtils;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.view.adapter.ChatFaceRecyclerAdapter;
import com.bawei6.usermodule.view.adapter.MyChatRecylerAdapter;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.entity.MsgEntity;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.iceteck.silicompressorr.SiliCompressor;
import com.ilike.voicerecorder.widget.VoiceRecorderView;

import org.jivesoftware.smack.chat2.Chat;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends BaseActivity {

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
    private List<String> slist = new ArrayList<>();

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 101) {
                //图片压缩处理
                String localPath = msg.obj.toString();
                final String fileName = createFileName();
                AliyunUtils.getInstance().upload(BaseConstant.ALI_BUCKETNAME, "img/" + fileName + ".png", localPath, new OSSCompletedCallback() {
                    @Override
                    public void onSuccess(OSSRequest request, OSSResult result) {
                        XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(chat, BaseConstant.ALI_FILE_PATH + "img/" + fileName + ".png");
                    }

                    @Override
                    public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {

                    }
                });
                refreshAdapter(localPath, MsgEntity.MsgType.Img);
            } else if (msg.what == 102) {
                //视频压缩处理
                String localPath = msg.obj.toString();
                final String fileName = createFileName();
                AliyunUtils.getInstance().upload(BaseConstant.ALI_BUCKETNAME, "video/" + fileName + ".mp4", localPath, new OSSCompletedCallback() {
                    @Override
                    public void onSuccess(OSSRequest request, OSSResult result) {
                        XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(chat, BaseConstant.ALI_FILE_PATH + "video/" + fileName + ".mp4");
                    }

                    @Override
                    public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {

                    }
                });

            }
        }
    };
    private RecyclerView chat_face_recyclerView;
    private ChatFaceRecyclerAdapter chatFaceRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        initData();
        initEvent();
        initListener();
        tousername = getIntent().getStringExtra("tousername");
        if (tousername != null) {
            text_toUsername.setText(tousername);
        }
    }

    private void initData() {
        slist.add("\uD83D\uDE00");
        slist.add("\uD83D\uDE01");
        slist.add("\uD83D\uDE02");
        slist.add("\uD83D\uDE03");
        slist.add("\uD83D\uDE04");
        slist.add("\uD83D\uDE05");
        slist.add("\uD83D\uDE06");
        slist.add("\uD83D\uDE07");
        slist.add("\uD83D\uDE08");
        slist.add("\uD83D\uDE09");
        slist.add("\uD83D\uDE10");
        slist.add("\uD83D\uDE11");
        slist.add("\uD83D\uDE12");
        slist.add("\uD83D\uDE13");
        slist.add("\uD83D\uDE14");
        slist.add("\uD83D\uDE15");
        slist.add("\uD83D\uDE16");
        slist.add("\uD83D\uDE17");
        slist.add("\uD83D\uDE18");
        slist.add("\uD83D\uDE19");
        slist.add("\uD83D\uDE20");
        slist.add("\uD83D\uDC66");
        slist.add("\uD83D\uDC67");
        slist.add("\uD83D\uDC68");
        slist.add("\uD83D\uDC69");
        slist.add("\uD83D\uDC70");
        slist.add("\uD83D\uDC71");
        slist.add("\uD83D\uDC72");
        slist.add("\uD83D\uDC73");
        slist.add("\uD83D\uDC74");
        slist.add("\uD83D\uDC75");
        slist.add("\uD83D\uDC76");
        slist.add("\uD83D\uDC77");
        slist.add("\uD83D\uDC78");
        slist.add("\uD83C\uDF85");
        slist.add("\uD83D\uDC8F");
        slist.add("\uD83D\uDC91");
        slist.add("\uD83D\uDC6A");
        slist.add("\uD83D\uDC7C");
        slist.add("\uD83D\uDC86");
        slist.add("\uD83D\uDC87");
        slist.add("\uD83D\uDE4D");
        slist.add("\uD83D\uDE4C");
        slist.add("\uD83D\uDE4F");
        slist.add("\uD83D\uDC63");
        slist.add("\uD83D\uDEAD");
        slist.add("\uD83D\uDE48");
        slist.add("\uD83D\uDE49");
        slist.add("\uD83D\uDC36");

        chat_face_recyclerView.setLayoutManager(new GridLayoutManager(this,10));
        chatFaceRecyclerAdapter = new ChatFaceRecyclerAdapter(slist);
        chatFaceRecyclerAdapter.setOnItemClickListener(new ChatFaceRecyclerAdapter.OnItemClickLisenter() {
            @Override
            public void onItemClick(int postion) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(chat_edit_sendMessage.getText().toString());
                stringBuilder.append(slist.get(postion));
                chat_edit_sendMessage.setText(stringBuilder.toString());
            }
        });
        chat_face_recyclerView.setAdapter(chatFaceRecyclerAdapter);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void initEvent() {
        //返回
        chat_image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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
                        AliyunUtils.getInstance().upload(BaseConstant.ALI_BUCKETNAME, "audio/" + fileName + ".mp3", voiceFilePath, new OSSCompletedCallback() {

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
        //发送图片
        image_sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertView("发送图片", null, "取消", null,
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

        //发送视频

        image_sendCupture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertView("发送视频", null, "取消", null, new String[]{"录制", "从相册中选择"}, ChatActivity.this, AlertView.Style.ActionSheet, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        switch (position) {
                            case 0:

                                break;
                            /**
                             * 相册
                             */
                            case 1:
                                Intent intent2 = new Intent();
                                intent2.setAction(Intent.ACTION_PICK);
                                intent2.setType("video/*");
                                startActivityForResult(intent2, 104);
                                break;
                            default:

                        }
                    }
                }).setCancelable(true).show();
            }
        });

        image_sendFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chat_face_recyclerView.getVisibility() == View.VISIBLE){
                    chat_face_recyclerView.setVisibility(View.GONE);
                }else {
                    chat_face_recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void refreshAdapter(String message, MsgEntity.MsgType msgType) {
        Log.d("sendMessage", "===========" + message);
        myChatRecylerAdapter.setMsgEntities(new MsgEntity(tousername, BaseConstant.userName, message, msgType));
        chat_recyclerView.scrollToPosition(myChatRecylerAdapter.getItemCount() - 1);
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


        chat_face_recyclerView = (RecyclerView) findViewById(R.id.chat_face_recyclerView);

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
                                chat_recyclerView.scrollToPosition(myChatRecylerAdapter.getItemCount() - 1);
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

            compressImage(localPath);
        } else if (requestCode == 101 && resultCode == RESULT_OK) {
            compressImage(imagePath);
        } else if (requestCode == 104 && resultCode == RESULT_OK) {

            String videoPath = null;
            String[] proj = {MediaStore.Video.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(), proj, null, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                videoPath = cursor.getString(column_index);
            }
            cursor.close();
            compressVideo(videoPath);
        }
    }

    //视频压缩
    private void compressVideo(final String videoPath) {
        refreshAdapter(videoPath, MsgEntity.MsgType.Video);
        ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                String compressPath = null;
                try {
                    compressPath = SiliCompressor.with(ChatActivity.this).compressVideo(videoPath, "/sdcard/Movies");
                    if (compressPath != null) {
                        Message obtain = Message.obtain();
                        obtain.what = 102;
                        obtain.obj = compressPath;
                        handler.sendMessage(obtain);
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //图片压缩
    private void compressImage(final String imagePath) {
        ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                String compressPath = SiliCompressor.with(ChatActivity.this).compress(imagePath, new File("/sdcard/DCIM/Camera/"));
                if (compressPath != null) {
                    Message obtain = Message.obtain();
                    obtain.what = 101;
                    obtain.obj = compressPath;
                    handler.sendMessage(obtain);
                }
            }
        });
    }
}
