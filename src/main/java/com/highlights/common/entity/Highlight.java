package com.highlights.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.format.SignStyle;

import static java.time.temporal.ChronoField.*;

@Document("highlights")
public class Highlight {
    @Id
    private String id;
    private String userId;
    private String contentId;
    private String text;
    private Location location;
    private Context context;
    private String type;
    private String source;
    private Trim trim;
    public LocalDateTime createdOn;
    public LocalDateTime updatedOn;
    public boolean accessed;

    @Transient
    String contentTitle;
    @Transient
    String contentImgUrl;
    @Transient
    String contentLaunchUrl;

    static DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
            .appendLiteral('-')
            .appendValue(MONTH_OF_YEAR, 2)
            .appendLiteral('-')
            .appendValue(DAY_OF_MONTH, 2)
            .appendLiteral(' ')
            .appendValue(HOUR_OF_DAY, 2)
            .appendLiteral(':')
            .appendValue(MINUTE_OF_HOUR, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(SECOND_OF_MINUTE, 2)
            .toFormatter();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedOn() {
        return createdOn.format(formatter);
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        if(updatedOn != null) {
            return updatedOn.format(formatter);
        }
        return null;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

    public boolean getAccessed() { return accessed; }

    public void setAccessed(boolean accessed) { this.accessed = accessed; }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentImgUrl() {
        return contentImgUrl;
    }

    public void setContentImgUrl(String contentImgUrl) {
        this.contentImgUrl = contentImgUrl;
    }

    public String getContentLaunchUrl() {
        return contentLaunchUrl;
    }

    public void setContentLaunchUrl(String contentLaunchUrl) {
        this.contentLaunchUrl = contentLaunchUrl;
    }

    public static final class HighlightBuilder {
        public LocalDateTime createdOn;
        public LocalDateTime updatedOn;
        private String id;
        private String userId;
        private String contentId;
        private String text;
        private Location location;
        private Context context;
        private String type;
        private String source;
        private Trim trim;

        private HighlightBuilder() {
        }

        public static HighlightBuilder aHighlight() {
            return new HighlightBuilder();
        }

        public HighlightBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public HighlightBuilder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public HighlightBuilder withContentId(String contentId) {
            this.contentId = contentId;
            return this;
        }

        public HighlightBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public HighlightBuilder withLocation(Location location) {
            this.location = location;
            return this;
        }

        public HighlightBuilder withContext(Context context) {
            this.context = context;
            return this;
        }

        public HighlightBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public HighlightBuilder withSource(String source) {
            this.source = source;
            return this;
        }

        public HighlightBuilder withTrim(Trim trim) {
            this.trim = trim;
            return this;
        }

        public HighlightBuilder withCreatedOn(LocalDateTime createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public HighlightBuilder withUpdatedOn(LocalDateTime updatedOn) {
            this.updatedOn = updatedOn;
            return this;
        }

        public Highlight build() {
            Highlight highlight = new Highlight();
            highlight.setId(id);
            highlight.setUserId(userId);
            highlight.setContentId(contentId);
            highlight.setText(text);
            highlight.setLocation(location);
            highlight.setContext(context);
            highlight.setType(type);
            highlight.setSource(source);
            highlight.setTrim(trim);
            highlight.setCreatedOn(createdOn);
            highlight.setUpdatedOn(updatedOn);
            return highlight;
        }
    }
}





