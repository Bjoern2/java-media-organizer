package com.github.bjoern2.photo.organizer.processor;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class TargetFilenameProcessor implements ItemProcessor<MediaData, MediaData> {

    private String basePath;
    private String expression = "getRecordingDate('yyyy') + '/' + (eventName == null ? getRecordingDate('MM') : eventName) + '/' + inputFile.name";
    
    
    @Override
    public MediaData process(MediaData item) throws Exception {
        
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression(expression);
        String targetPath = (String)exp.getValue(item);
        
        File file = new File(FilenameUtils.concat(basePath, targetPath));
        item.setOutputFile(file);
        
        return item;
    }


    public String getBasePath() {
        return basePath;
    }


    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }


    public String getExpression() {
        return expression;
    }


    public void setExpression(String expression) {
        this.expression = expression;
    }

}
