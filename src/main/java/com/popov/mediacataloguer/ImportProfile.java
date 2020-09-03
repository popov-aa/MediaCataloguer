package com.popov.mediacataloguer;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
public class ImportProfile {
    private String title;
    private Optional<String> inputDirPath = Optional.empty();
    private boolean requestInputDirPath = false;
    private Map<MediaType,String> outputDirPaths = new HashMap<>();
    private boolean requestOutputDirPath = false;
}
