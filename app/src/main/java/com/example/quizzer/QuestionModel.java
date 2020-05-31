package com.example.quizzer;

public class QuestionModel {
    private String question,imageurl, option1, option2, option3, option4, CorrectANS;
    private int setNo;

    public QuestionModel() {
        //for firebase
    }

    public QuestionModel(String question, String option1,
                         String option2, String option3, String option4,
                          String correctANS, int setNo,String imageurl) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.CorrectANS = correctANS;
        this.setNo = setNo;
        this.imageurl=imageurl;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getOption1()
    {
        return option1;
    }

    public void setOption1(String option1)
    {
        this.option1 = option1;
    }

    public String getOption2()
    {
        return option2;
    }

    public void setOption2(String option2)
    {
        this.option2 = option2;
    }

    public String getOption3()
    {
        return option3;
    }

    public void setOption3(String option3)
    {
        this.option3 = option3;
    }

    public String getOption4()
    {
        return option4;
    }

    public void setOption4(String option4)
    {
        this.option4 = option4;
    }

    public String getCorrectANS()
    {
        return this.CorrectANS;
    }

    public void setCorrectANS(String correctANS)
    {
        CorrectANS = correctANS;
    }

    public int getSetno()
    {
        return setNo;
    }

    public void setSetno(int setno)
    {
        this.setNo = setno;
    }
    public String getImageurl()
    {
        return imageurl;
    }

    public void setImageurl(String imageurl)
    {
        this.imageurl = imageurl;
    }
}