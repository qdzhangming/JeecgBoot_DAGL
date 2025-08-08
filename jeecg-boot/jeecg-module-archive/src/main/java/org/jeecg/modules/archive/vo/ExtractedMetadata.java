package org.jeecg.modules.archive.vo;

import lombok.Data;

@Data
public class ExtractedMetadata {
    private String title;
    private String author;
    private String creationDate; // Using String to avoid date parsing issues from AI
    private boolean potentialDuplicate;
    private String duplicateReason;
}
