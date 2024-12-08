const WindyWidget = () => {
    return (
        <div>
            <iframe
                title="windy-map"
                width="100%"
                height="1000px"
                src="https://embed.windy.com/embed2.html?lat=16.0471&lon=108.2062&detailLat=16.0471&detailLon=108.2062&width=650&height=450&zoom=5&level=surface&overlay=wind&product=ecmwf&menu=&message=true&marker=true&calendar=12&pressure=&type=map&location=coordinates&detail=true&metricWind=default&metricTemp=default&radarRange=-1"
                frameBorder="0"
            />
        </div>
    );
};

export default WindyWidget;
