package com.popov.mediacataloguer.service;

import com.popov.mediacataloguer.ImportProfile;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Settings {
    private List<ImportProfile> importProfiles = new ArrayList<>();
}
