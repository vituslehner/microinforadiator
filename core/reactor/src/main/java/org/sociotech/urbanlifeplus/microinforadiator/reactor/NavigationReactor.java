/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.reactor;

import com.esri.core.geometry.OperatorDistance;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.CoreConfiguration;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity;
import org.sociotech.urbanlifeplus.microinforadiator.model.Route;
import org.sociotech.urbanlifeplus.microinforadiator.model.User;
import org.sociotech.urbanlifeplus.microinforadiator.model.WayPoint;
import org.sociotech.urbanlifeplus.microinforadiator.model.event.UserProximityEvent;
import org.sociotech.urbanlifeplus.microinforadiator.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.sociotech.urbanlifeplus.microinforadiator.interfaces.proximity.Proximity.NEAR;

/**
 * Reactor that listens to internal and external proximity events and accordingly updates interfaces
 * to navigate users to their destinations.
 *
 * @author vituslehner 04.07.17
 */
@Service
public class NavigationReactor {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationReactor.class);

    private static final double DISTANCE_TOLERANCE = 0.00013d;

    private final CoreConfiguration coreConfiguration;
    private final LightService lightService;
    private final EventBus reactorEventBus;

    @Autowired
    public NavigationReactor(CoreConfiguration coreConfiguration, LightService lightService, @Qualifier("reactorEventBus") EventBus reactorEventBus) {
        this.coreConfiguration = coreConfiguration;
        this.lightService = lightService;
        this.reactorEventBus = reactorEventBus;
        this.reactorEventBus.register(this);
    }

    /**
     * Listen to reactor proximity events and update light and arrow interfaces accordingly.
     * Uses e.g. Google Maps APIs to calculate route information and identifier location on route.
     *
     * @param event the proximity event
     */
    @Subscribe
    public void proximityEvent(UserProximityEvent event) {
        LOGGER.debug("Receiving reactor proximity event: {}", event);

        User user = event.getUser();
        Proximity proximity = event.getProximity();

        if (proximity == NEAR && isLocalEvent(event)) {
            // TODO light signal
        }

        if(user != null && user.getRoute() != null && user.getColor() != null) {
            handleRoute(user);
        }
    }

    private void handleRoute(User user) {
        Polyline route = convertRouteToPolyline(user.getRoute());
        WayPoint mirPosition = coreConfiguration.getMirPositionAsWayPoint();
        Point mirPoint = convertWayPointToPoint(mirPosition);

        LOGGER.debug("Route-Comparision. Polyline: {} Point: {}", route, mirPoint);
        double distance = calculateDistance(mirPoint, route);
        LOGGER.debug("Distance: {}", distance);
        if (distance <= DISTANCE_TOLERANCE) {
            LOGGER.debug("Point {} is on route {}.", mirPoint, route);
            lightService.addColor(user.getColor());
        } else {
            LOGGER.debug("Point {} is not on route {}.", mirPoint, route);
            lightService.removeColor(user.getColor());
        }
    }

    private Polyline convertRouteToPolyline(Route route) {
        List<WayPoint> wayPoints = route.getWayPoints();
        Polyline line = new Polyline();
        if (wayPoints != null && wayPoints.size() >= 2) {
            line.startPath(convertWayPointToPoint(wayPoints.get(0)));
            for (int i = 1; i < wayPoints.size(); i++) {
                line.lineTo(convertWayPointToPoint(wayPoints.get(i)));
            }
        }
        return line;
    }

    private Point convertWayPointToPoint(WayPoint wayPoint) {
        return new Point(wayPoint.getLat(), wayPoint.getLng());
    }

    private double calculateDistance(Point point, Polyline line) {
        return OperatorDistance.local().execute(line, point, null);
    }

    private boolean isLocalEvent(UserProximityEvent event) {
        return coreConfiguration.getMirId().equals(event.getSourceMirId());
    }

}
