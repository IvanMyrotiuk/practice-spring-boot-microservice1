package com.java.myrotiuk.helper;

import com.java.myrotiuk.service.TourPackageService;
import com.java.myrotiuk.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Ivan_Myrotiuk
 */
@Component
@RequiredArgsConstructor
public class LoadDataHelper {

    @Value("${ec.importfile}")
    private String importFile;

    private final TourPackageService tourPackageService;
    private final TourService tourService;

    public void createData() throws IOException {
        createTourPackages();
        //long numOfTourPackages = tourPackageService.total();
        createTours(importFile);
        //long numOfTours = tourService.total();
    }


    /**
     * Initialize all the known tour packages
     */
    private void createTourPackages(){
        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");
    }

    /**
     * Create tour entities from an external file
     */
    private void createTours(String fileToImport) throws IOException {
        TourFromFileHelper.read(fileToImport).forEach(importedTour ->
                tourService.createTour(importedTour.getTitle(),
                        importedTour.getDescription(),
                        importedTour.getBlurb(),
                        importedTour.getPrice(),
                        importedTour.getLength(),
                        importedTour.getBullets(),
                        importedTour.getKeywords(),
                        importedTour.getPackageType(),
                        importedTour.getDifficulty(),
                        importedTour.getRegion()));
    }

}
