class PersonTracker {
    ...
    Location lastLocation = null;

    // called every second
    void updateLocation( Location l ) throws GPSException {
        if (lastLocation != null) {
            if (lastLocation.distance( l ) > 200.0*1000/3600) {
                // 200 km/h in m/s
                GPS.getGPS().disable( 200 );
                throw new GPSException( "Too Fast!" );
            }
        }
        lastLocation = l;
    }
}

interface Location {
    // distance in metres
    public double distance( Location l );
}

class TestPersonTracker extends TestCase {
    void testTooFast() {
        PersonTracker p = new PersonTracker();
        Location l = new MockLocation( 0, 0 );
        try {
            p.updateLocation( l );
            p.updateLocation( l );
            fail( "This was supposed to throw exception." );
        } catch (GPSException e) {
            return; // exception was properly thrown
        }
    }
}

class  MockLocation implements Location{
    double lon;
    double lat;
    MockLocation (double lon, double lat){
        this.lon=lon;
        this.lat=lat;
    }
    public double distance(Location 1){
        return 200.0*1000/3600+0.1;

    }

}
