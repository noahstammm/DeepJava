package ch.zhaw.stammnoa.mdm_two.model;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.output.DetectedObjects;

public record Prediction(Image image, DetectedObjects detectedObjects) {

    public Image getImage() {
        return image;
    }

    public DetectedObjects getDetectedObjects() {
        return detectedObjects;
    }
}
