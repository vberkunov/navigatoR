package managment;

import com.impinj.octane.*;


public class ReaderManagment {
    private  String hostname;
    private final ImpinjReader reader;
    private boolean isStarted = false;
    private int k = 0;


    public ReaderManagment(String hostname) {
        this.hostname = hostname;
        this.reader = new ImpinjReader();
    }

    public void checkConn() {

        if (isStarted){
            boolean isConn = false;
            try {
                reader.getAccessSpec();
            } catch (OctaneSdkException e) {
                System.out.println("Impinj Error");
            }
                    }
    }

    public void applySettings(){
        try {
            Settings settings = reader.queryDefaultSettings();
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

    public void start() throws OctaneSdkException {
        reader.setTagReportListener(new TagReportListener() {
            @Override
            public void onTagReported(ImpinjReader impinjReader, TagReport tagReport) {
                    tagReport.getTags();
            }
        });
        reader.start();
    }

    public void stop() throws OctaneSdkException {
        isStarted = false;
        reader.stop();
        reader.disconnect();
    }


    public boolean connect() {
        try {

            reader.connect(hostname, 5084);
        } catch (OctaneSdkException e){
            return false;
        }
        return true;
    }

}
