package com.amannmalik.service.example;

import org.xbill.DNS.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Amann on 6/10/2015.
 */
public class DnsLookup {

    protected static List<String> getSearchSuffixes() {
        Name[] defaultSearchPaths = Lookup.getDefaultSearchPath();
        return Arrays.asList(defaultSearchPaths).stream()
                .map(Name::toString)
                .collect(Collectors.toList());
    }

    protected static List<ServerAddress> getServiceRecords(String srvName) {
        Lookup lookup;
        try {
            lookup = new Lookup(srvName, Type.SRV);
        } catch (TextParseException e) {
            throw new RuntimeException(e);
        }
        Record[] results = lookup.run();
        if (results == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(results).stream()
                .map(r -> (SRVRecord) r)
                .map(r -> new ServerAddress(r.getTarget().toString(), r.getPort()))
                .collect(Collectors.toList());
    }
}