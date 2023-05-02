package ch.zhaw.stammnoa.mdm_two.model;

import ai.djl.ModelException;
import ai.djl.modality.cv.*;
import ai.djl.modality.cv.output.*;
import ai.djl.repository.zoo.*;
import ai.djl.training.util.*;
import ai.djl.translate.TranslateException;
import org.slf4j.Logger;

import java.io.IOException;

public class SsdModel {
    // Example: https://docs.djl.ai/jupyter/object_detection_with_model_zoo.html
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(SsdModel.class);
    private final ZooModel<Image, DetectedObjects> model;

    public SsdModel() {
        try {
            this.model = ModelZoo.loadModel(Criteria.builder()
                    .setTypes(Image.class, DetectedObjects.class)
                    .optArtifactId("ssd") // https://modelzoo.co/model/ssd
                    .optProgress(new ProgressBar())
                    .build());
            logger.info("Model loaded");
        } catch (Exception e) {
            logger.error("Error loading model", e);
            throw new RuntimeException(e);
        }
    }

    public Prediction predict(Image img) throws TranslateException {
        logger.info("Predicting");
        var detections = model.newPredictor().predict(img);

        // check detected result
        img.drawBoundingBoxes(detections);
        img.getWrappedImage();

        logger.info("{}", detections);
        return new Prediction(img, detections);
    }
}
