package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

public class Empty {
    @SerializedName("signedUrl")
    private String signedUrl = null;


    @ApiModelProperty(value = "")
    public String getSignedUrl() {
        return signedUrl;
    }

    public void setSignedUrl(String signedUrl) {
        this.signedUrl = signedUrl;
    }

    public Empty signedUrl(String signedUrl){
        this.signedUrl = signedUrl;
        return this;
    }
}
