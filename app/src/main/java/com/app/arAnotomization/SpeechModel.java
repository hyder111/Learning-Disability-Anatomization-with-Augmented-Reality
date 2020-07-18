package com.app.arAnotomization;

public class SpeechModel {
    public SpeechModel() {
    }
    private String question,Imageurl;
    private int setNo;
    public SpeechModel(String question, String Imageurl, int setNo) {
        this.question = question;
        this.Imageurl = Imageurl;
        this.setNo = setNo;
    }



    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImageurl() {
        return Imageurl;
    }

    public void setImageurl(String Imageurl) {
        this.Imageurl = Imageurl;
    }

    public int getSetNo() {
        return setNo;
    }

    public void setSetNo(int setNo) {
        this.setNo = setNo;
    }
}
