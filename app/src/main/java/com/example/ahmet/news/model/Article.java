
package com.example.ahmet.news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Article {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("ContentType")
    @Expose
    private String contentType;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Editor")
    @Expose
    private String editor;
    @SerializedName("Files")
    @Expose
    private List<File> files = null;
    @SerializedName("ModifiedDate")
    @Expose
    private String modifiedDate;
    @SerializedName("Path")
    @Expose
    private String path;
    @SerializedName("RelatedNews")
    @Expose
    private List<Object> relatedNews = null;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("Tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("Text")
    @Expose
    private String text;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Url")
    @Expose
    private String url;
    @SerializedName("Writers")
    @Expose
    private List<Object> writers = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Object> getRelatedNews() {
        return relatedNews;
    }

    public void setRelatedNews(List<Object> relatedNews) {
        this.relatedNews = relatedNews;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Object> getWriters() {
        return writers;
    }

    public void setWriters(List<Object> writers) {
        this.writers = writers;
    }

}