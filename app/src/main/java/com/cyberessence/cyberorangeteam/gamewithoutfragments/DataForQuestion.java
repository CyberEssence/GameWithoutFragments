package com.cyberessence.cyberorangeteam.gamewithoutfragments;

public class DataForQuestion {

    static int perfect = R.raw.perfect;
    static int wrong = R.raw.wrong;
    static int applause = R.raw.applause;

    private String question;
    private String type;
    private int [] imgId;
    private int  soundId;
    private int answer;


    private  int[] imgQ1 = {R.drawable.activity1,R.drawable.activity2,R.drawable.activity5};

    public static DataForQuestion [] dataForQuestionsAction = new DataForQuestion[]{
            new DataForQuestion("На какой картинке мальчик ест?","Action",
                    new int[]{R.drawable.activity1,R.drawable.activity2,R.drawable.activity5},
                    R.raw.eating, 2),
            new DataForQuestion("На какой картинке мальчик ходит?","Action",
                    new int[]{R.drawable.walking,R.drawable.activity6,R.drawable.activity2},
                    R.raw.walking, 1),
            new DataForQuestion("На какой картинке мальчик рисует?","Action",
                    new int[]{R.drawable.jumping,R.drawable.drawing,R.drawable.eating},
                    R.raw.drawing, 2)
    };

    public static DataForQuestion [] dataForQuestionsFood = new DataForQuestion[]{
            new DataForQuestion("На какой картинке помидор?","Food",
                    new int[]{R.drawable.food1,R.drawable.food5,R.drawable.food2},
                    R.raw.tomat, 1),
            new DataForQuestion("На какой картинке чай?","Food",
                     new int[]{R.drawable.food3,R.drawable.fruits2,R.drawable.tea},
                     R.raw.tea, 3),
            new DataForQuestion("На какой картинке суп?","Food",
                      new int[]{R.drawable.fruits6,R.drawable.soup,R.drawable.food4},
                      R.raw.soup, 2)
    };

    public DataForQuestion( String question,String type, int [] imgId, int soundId, int answer) {
        this.question = question;
        this.imgId = imgId;
        this.soundId = soundId;
        this.answer = answer;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public int[] getImgId() {
        return imgId;
    }

    public int getSoundId() {
        return soundId;
    }

    public int getAnswer() {
        return answer;
    }
}
