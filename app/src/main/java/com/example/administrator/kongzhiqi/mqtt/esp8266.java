package com.example.administrator.kongzhiqi.mqtt;

/**
 * Created by Administrator on 2018/11/9/0009.
 */

public class esp8266 {
    /**
     * bianhao : 0001
     * kg_bianhao : 01
     * dongzuo : qidong
     */

    private String bianhao;
    private String kg_bianhao;
    private String dongzuo;
    private String chakan;

    public esp8266(String bianhao, String kg_bianhao, String dongzuo) {
        this.bianhao = bianhao;
        this.kg_bianhao = kg_bianhao;
        this.dongzuo = dongzuo;
    }
    public esp8266(String bianhao, String chakan) {
        this.bianhao = bianhao;
        this.chakan = chakan;
    }
    public esp8266() {

    }

    public String getChakan() {
        return chakan;
    }
    public void setChakan(String chakan)
    {this.chakan = chakan;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao;
    }

    public String getKg_bianhao() {
        return kg_bianhao;
    }

    public void setKg_bianhao(String kg_bianhao) {
        this.kg_bianhao = kg_bianhao;
    }

    public String getDongzuo() {
        return dongzuo;
    }

    public void setDongzuo(String dongzuo) {
        this.dongzuo = dongzuo;
    }
}
