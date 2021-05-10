package com.locationService.locationService.service.implementation;

import com.impinj.octane.ImpinjReader;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;
import com.locationService.locationService.repository.TagRepository;

import java.util.ArrayList;

public class RSSIReportListener implements TagReportListener {

    private TagRepository tagRepository;

    private double St1 = 0D;
    private double St2 = 0D;
    private double St3 = 0D;
    private double St4 = 0D;

    public RSSIReportListener(TagRepository repository) {
        this.tagRepository = repository;
    }

    @Override
    public void onTagReported(ImpinjReader impinjReader, TagReport tagReport) {
        for (Tag tag: tagReport.getTags()
        ) {
            switch (tag.getAntennaPortNumber()){
                case 1:
                    St1 = movingAvarage(St1, tag.getPeakRssiInDbm());
                case 2:
                    St2 = movingAvarage(St2, tag.getPeakRssiInDbm());
                case 3:
                    St3 = movingAvarage(St3, tag.getPeakRssiInDbm());
                case 4:
                    St4 = movingAvarage(St4, tag.getPeakRssiInDbm());
            }

            calculateCoordinates(St1,St2, St3, St4);
        }
    }

    private void calculateCoordinates(double st1, double st2, double st3, double st4) {
        double x;
        double y;
    }


    private static double movingAvarage(double St, double Yt) {
        double alpha = 0.01, oneMinusAlpha = 0.99;
        if(St <= 0D) {
            St = Yt;
        } else {
            St = alpha*Yt + oneMinusAlpha*St;
        }
        return St;
    }







    }


