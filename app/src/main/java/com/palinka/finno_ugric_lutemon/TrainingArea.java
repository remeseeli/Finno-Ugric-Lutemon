package com.palinka.finno_ugric_lutemon;

/**
 * @methods train
 */
public class TrainingArea {
    /**
     *
     * @param lutemon
     * @throws InterruptedException
     */
    public void train(Lutemon lutemon) throws InterruptedException {
        try{
            Thread.sleep(1000);
            int newExperience = lutemon.getExperience() + 10;
            lutemon.setExperience(newExperience);
        }catch (InterruptedException e) {
            throw new InterruptedException("Training was interrupted: " + e.getMessage());
        }
    }
}
