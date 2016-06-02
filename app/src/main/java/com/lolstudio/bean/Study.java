package com.lolstudio.bean;

/**
 * Created by andrew on 2016/5/31.
 */
public class Study {
    public static final int CHOICE_XML = 0, MATE_XML = 1;//1是選擇題，2是配對題
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
        public Answer(int answerType, String answer, String[] value) {
            this.answer = answer;
            mValue = value;
            this.answerType = answerType;

        }
        public int selectionIndex;

        public int getSelectionIndex() {
            return selectionIndex;
        }

        public void setSelectionIndex(int selectionIndex) {
            this.selectionIndex = selectionIndex;
        }

        public int answerType;

        public int getAnswerType() {
            return answerType;
        }

        public void setAnswerType(int answerType) {
            this.answerType = answerType;
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
