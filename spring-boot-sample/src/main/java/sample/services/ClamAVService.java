package sample.services;


import clamav.SimpleScanResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.capybara.clamav.ClamavClient;
import xyz.capybara.clamav.commands.scan.result.ScanResult;
import xyz.capybara.clamav.exceptions.ClamavException;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ClamAVService {
    @Value("${clamav.host}")
    private String clamavHost;

    @Value("${clamav.port}")
    private Integer clamavPort;

    private ClamavClient client;

    @PostConstruct
    public void initBean() {
        client = new ClamavClient(clamavHost, clamavPort);
    }

    public SimpleScanResult scanForViruses(InputStream inputStream) throws ClamavException {
        ScanResult result = client.scan(inputStream);
        if(ScanResult.Status.VIRUS_FOUND.equals(result.getStatus())) {
            return new SimpleScanResult(result.getFoundViruses().values().stream().flatMap(Collection::stream).collect(Collectors.toList()));
        } else {
            return new SimpleScanResult(null);
        }
    }

    public String getVersion() throws ClamavException {
        return client.version();
    }
}
