package com.lolstudio.bean;

/**
 * Created by andrew on 2016/5/31.
 */
public class Study {
    private String title;
    private Answer mAnswers;
    private boolean isError;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public Study(String title, Answer answers) {
        this.title = title;
        mAnswers = answers;
    }

    public Answer getAnswers() {
        return mAnswers;
    }

    public void setAnswers(Answer answers) {
        mAnswers = answers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class Answer {
        public Answer(String answer, String[] value) {
            this.answer = answer;
            mValue = value;
        }

        public String[] getValue() {
            return mValue;
        }

        public void setValue(String[] value) {
            mValue = value;
        }

        private String answer;//正确答案
        private String[] mValue;
        private String id;


        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {

            this.id = id;
        }

    }
}
