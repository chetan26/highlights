package com.highlights.common.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    public String createdOn;
    public String updatedOn;

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
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }


    public static final class HighlightBuilder {
        public String createdOn;
        public String updatedOn;
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

        public HighlightBuilder withCreatedOn(String createdOn) {
            this.createdOn = createdOn;
            return this;
        }

        public HighlightBuilder withUpdatedOn(String updatedOn) {
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





