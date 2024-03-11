package com.test.entity.vo;

import java.util.List;

public class CatModel {
    private List<String> names;
    private List<byte[]> images;
    private List<Integer> albumIds;
    private List<Integer> ages;
    private List<String> varieties;
    private List<String> genders;
    private List<String> colors;
    private List<String> neutereds;
    private List<String> healthies;
    private List<String> addresses;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<byte[]> getImages() {
        return images;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public List<Integer> getAlbumIds() {
        return albumIds;
    }

    public void setAlbumIds(List<Integer> albumIds) {
        this.albumIds = albumIds;
    }

    public List<Integer> getAges() {
        return ages;
    }

    public void setAges(List<Integer> ages) {
        this.ages = ages;
    }

    public List<String> getVarieties() {
        return varieties;
    }

    public void setVarieties(List<String> varieties) {
        this.varieties = varieties;
    }

    public List<String> getGenders() {
        return genders;
    }

    public void setGenders(List<String> genders) {
        this.genders = genders;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<String> getNeutereds() {
        return neutereds;
    }

    public void setNeutereds(List<String> neutereds) {
        this.neutereds = neutereds;
    }

    public List<String> getHealthies() {
        return healthies;
    }

    public void setHealthies(List<String> healthies) {
        this.healthies = healthies;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
