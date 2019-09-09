package clamav;


import java.util.List;

public class SimpleScanResult {
    private boolean hasViruses;
    private List<String> viruses;

    public SimpleScanResult() {
    }

    public SimpleScanResult(List<String> viruses) {
        this.hasViruses = viruses != null && viruses.size() != 0;
        this.viruses = viruses;
    }

    public boolean isHasViruses() {
        return hasViruses;
    }

    public void setHasViruses(boolean hasViruses) {
        this.hasViruses = hasViruses;
    }

    public List<String> getViruses() {
        return viruses;
    }

    public void setViruses(List<String> viruses) {
        this.viruses = viruses;
    }
}

