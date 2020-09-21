package com.popov.mediacataloguer.service;

import com.popov.mediacataloguer.ImportProfile;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Settings {

    @Getter @Setter
    private List<ImportProfile> importProfiles = new ArrayList<>();

}
