package cc.projectnexus.adapters.java.datamodels;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Region {
    EUROPE("Europe", "europe"),
    NORTH_AMERICA("North America", "na"),
    SOUTH_AMERICA("South America", "sa"),
    ASIA("Asia", "asia"),
    AFRICA("Africa", "africa"),
    OCEANIA("Oceania", "oceania");

    String displayName;
    String identifier;

    public static List<Region> getAllRegions() {
        return Arrays.stream(values()).collect(Collectors.toList());
    }

    public static List<Region> getRegionsByFilter(Predicate<Region> regionPredicate) {
        return getAllRegions().stream().filter(regionPredicate).collect(Collectors.toList());
    }
}
