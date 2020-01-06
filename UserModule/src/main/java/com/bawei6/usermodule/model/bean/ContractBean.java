package com.bawei6.usermodule.model.bean;

/**
 * @author AZhung
 * @date 2020/1/2
 * @description
 */
public class ContractBean {
    private String name;
    private String number;
    private String label;
    private int flag;


    public ContractBean(String name, String number, String label, int flag) {
        this.name = name;
        this.number = number;
        this.label = label;
        this.flag = flag;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "ContractBean{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", label='" + label + '\'' +
                ", flag=" + flag +
                '}';
    }
}
