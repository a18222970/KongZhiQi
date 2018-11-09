package com.example.administrator.kongzhiqi.mqtt;

/**
 * Created by Administrator on 2018/11/9/0009.
 */

public class esp8266_shou {
    /**
     * zhuangtai : tingzhi
     * sbbianhao : 0001
     * kg_bianhao : 01
     */

    private String zhuangtai;
    private String sbbianhao;
    private String kg_bianhao;
    private String chakan;
    private int jiancha;

    public esp8266_shou(String sbbianhao, String kg_bianhao, String zhuangtai) {
        this.sbbianhao = sbbianhao;
        this.kg_bianhao = kg_bianhao;
        this.zhuangtai = zhuangtai;
    }
    public esp8266_shou(String sbbianhao, String chakan) {
        this.sbbianhao = sbbianhao;
        this.chakan = chakan;
    }
    public esp8266_shou() {

    }
    public int getJiancha() {
        return jiancha;
    }
    public void setJiancha(int jiancha) {
        this.jiancha = jiancha;
    }

    public String getChakan() {
        return chakan;
    }
    public void setChakan(String chakan) {
        this.chakan = chakan;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getSbbianhao() {
        return sbbianhao;
    }

    public void setSbbianhao(String sbbianhao) {
        this.sbbianhao = sbbianhao;
    }

    public String getKg_bianhao() {
        return kg_bianhao;
    }

    public void setKg_bianhao(String kg_bianhao) {
        this.kg_bianhao = kg_bianhao;
    }
}
