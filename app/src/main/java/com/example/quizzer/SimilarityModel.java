package com.example.quizzer;

public class SimilarityModel
{
    public SimilarityModel() {
    }

    private String question, option1, option2, imageRurl,imageLurl, CorrectANS;
    private int setNo;

    public SimilarityModel(String question, String option1, String option2,
                           String imageRurl, String imageLurl
            , String correctANS, int setNo) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.imageRurl = imageRurl;
        this.imageLurl = imageLurl;
        CorrectANS = correctANS;
        this.setNo = setNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getImageRurl() {
        return imageRurl;
    }

    public void setImageRurl(String imageRurl) {
        this.imageRurl = imageRurl;
    }

    public String getImageLurl() {
        return imageLurl;
    }

    public void setImageLurl(String imageLurl) {
        this.imageLurl = imageLurl;
    }

    public String getCorrectANS() {
        return CorrectANS;
    }

    public void setCorrectANS(String correctANS) {
        CorrectANS = correctANS;
    }

    public int getSetNo() {
        return setNo;
    }

    public void setSetNo(int setNo) {
        this.setNo = setNo;
    }
}
