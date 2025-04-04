package com.palinka.finno_ugric_lutemon;

/**
 * This class represents the training area where Lutemons can train to gain experience.
 * @methods train
 */
public class TrainingArea {
    private long lastTrainingTime = 0;
    /**
     *This method allows a Lutemon to train and gain experience. The user can only train once every hour.
     * @param lutemon
     * @throws InterruptedException
     * @throws IllegalArgumentException
     */
    public void train(Lutemon lutemon) throws InterruptedException {
        long currentTime = System.currentTimeMillis();
        long oneHour = 3600000; // 1 hour in milliseconds
        //Check if the one hour has passed since the last training
        if (currentTime - lastTrainingTime < oneHour){
            throw new InterruptedException("You can only train once every hour.");
        }

        try{
            //train the lutemon for 1 second
            Thread.sleep(1000);
            lutemon.gainXP(10);
            lastTrainingTime = System.currentTimeMillis();
        }catch (InterruptedException e) {
            throw new InterruptedException("Training was interrupted: " + e.getMessage());
        }
    }
}