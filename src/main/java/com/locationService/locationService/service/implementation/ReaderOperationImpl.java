package com.locationService.locationService.service.implementation;


import com.impinj.octane.*;
import com.locationService.locationService.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class ReaderOperationImpl {

    private final ImpinjReader reader;
    private TagRepository tagRepository;


    public ReaderOperationImpl(TagRepository repository){

        this.reader = new ImpinjReader();
        this.tagRepository = repository;
    }

    public String connect(String hostname, int port) {
        try {
            reader.connect(hostname,port);

        } catch (OctaneSdkException e){
            return e.getMessage();
        }
        return "Connected";
    }

    public void start() throws OctaneSdkException {
        reader.setTagReportListener(new RSSIReportListener(tagRepository));
        reader.start();
    }

    public void stop() throws OctaneSdkException {
        reader.stop();
        reader.disconnect();
    }

    public void applySettings(String targetEpc){
        try {

            Settings settings = reader.queryDefaultSettings();

            TagFilter t1 = settings.getFilters().getTagFilter1();
            t1.setBitCount(16);
            t1.setBitPointer(BitPointers.Epc);
            t1.setMemoryBank(MemoryBank.Epc);
            t1.setFilterOp(TagFilterOp.Match);
            t1.setTagMask(targetEpc);
            settings.getFilters().setMode(TagFilterMode.OnlyFilter1);

            settings.getReport().setIncludeAntennaPortNumber(true);
            settings.getReport().setIncludeSeenCount(true);
            settings.getReport().setIncludeFirstSeenTime(true);
            settings.getReport().setIncludePeakRssi(true);
            settings.getReport().setIncludeFastId(true);
            settings.setSearchMode(SearchMode.ReaderSelected);
            settings.setReaderMode(ReaderMode.AutoSetDenseReader);
            settings.setSession(2);
            settings.getAutoStart().setMode(AutoStartMode.Immediate);
            settings.getAutoStop().setMode(AutoStopMode.None);
            settings.setHoldReportsOnDisconnect(true);
            settings.getKeepalives().setEnabled(true);
            settings.getKeepalives().setEnableLinkMonitorMode(true);

            ReportConfig reportConfig = new ReportConfig();
            reportConfig.setIncludeAntennaPortNumber(true);
            reportConfig.setIncludeFirstSeenTime(true);
            reportConfig.setIncludePeakRssi(true);

            reader.applySettings(settings);
            reader.saveSettings();
        }
        catch (OctaneSdkException e) {
            e.printStackTrace();
        }
    }





}
