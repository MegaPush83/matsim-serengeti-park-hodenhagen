package org.matsim.analysis;

import org.matsim.core.events.EventsUtils;

public class SimpleAnalysis {

    public static void main(String[] args) {

        var handler = new SimplePersonEventHandler();
        var manager = EventsUtils.createEventsManager();
        var linkVolumeHandler = new HourlyLinkVolumeHandler();
        int numberofhours = 0;
        double numberofvehicles = 0;

       // manager.addHandler(handler);
        manager.addHandler(linkVolumeHandler);

        EventsUtils.readEvents(manager, "/Users/hp/IdeaProjects/matsim-serengeti-park-hodenhagen/scenarios/serengeti-park-v1.0/output/output-serengeti-park-v1.0-run1/serengeti-park-v1.0-run1.output_events.xml.gz");

        for (var hours : linkVolumeHandler.hourToVehicles.keySet()){
            System.out.printf("%d: %d\n", hours, linkVolumeHandler.hourToVehicles.get(hours));
            numberofhours++;
            numberofvehicles = numberofvehicles + linkVolumeHandler.hourToVehicles.get(hours);
        }
        System.out.printf("Bei einer Gesamtzahl an Autos von %.0f und %d Stunden sind es im Schnitt:\n %.2f Autos pro Stunde", numberofvehicles, numberofhours, numberofvehicles / numberofhours);
    }
}
