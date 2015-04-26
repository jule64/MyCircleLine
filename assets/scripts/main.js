/**
 * Created by julienmonnier on 03/04/2015.
 */

function mainModule(){

    var trainInfoTagRef = '#trainInfoBox'
    var selectedTrainID = 'LST' // Liv Street station, default value
    var trainTimeout

    initStationsList = function(){

        var hasUrlReturned = false

        $.get("http://mycircleline.co.uk/allstations",
            function(data) {
                hasUrlReturned = true
                $('#loading-msg').empty()
                $('#left-list').empty()
                $('#right-list').empty()

                var halfListSize = data.length/2
                var station

                for(var i = 0;i<data.length;++i){
                    station = data[i]
                    if(i<halfListSize){
                        $("<h4/>").attr('onclick','selectedTrain('+"'"+station.code+"'"+')').html(station.name).appendTo('#left-list');
                    } else {
                        $("<h4/>").attr('onclick','selectedTrain('+"'"+station.code+"'"+')').html(station.name).appendTo('#right-list');
                    }
                }
            }
        ).error(
            console.log('connection prob')
        )

        setTimeout(function(){


            if(!hasUrlReturned){
                $('#loading-msg').empty()
                $('<h4/>').html('<i>Oops... we are down sorry!</i>').appendTo('#loading-msg')
            }

        }, 7000);

    }

    selectedTrain = function (selectedTrainID){

        clearTimeout(trainTimeout)

        $.get("http://mycircleline.co.uk/stations?code="+selectedTrainID,
            function(data) {
                $(trainInfoTagRef).empty();
                $("<h2/>").html(data.name).appendTo(trainInfoTagRef);
//                        $("<h2/>").html(data.name + " (updated ('"+data.curTime + ") seconds ago)").appendTo(trainInfoTagRef);

                data.platforms.forEach(function(p){
                    $("<h3/>").html('<i>'+p.name+'</i>').appendTo(trainInfoTagRef);

                    var trainRank = ["1st","2nd","3rd","4th"];
                    if(p.trains.length==0){
                        $("<p/>").attr('class','lead').attr('style','font-size: 16px;').html("<i>No Circle Line trains reported for this platform</i>").appendTo(trainInfoTagRef);
                    } else {
                        for(var i=0;i<p.trains.length;++i){
                            if(i<trainRank.length){

                                $("<p/>").attr('class','lead').attr('style','font-size: '+(20-(i*2))+'px;').html(trainRank[i]+" Circle Line train in " + p.trains[i].timeTo + " min").appendTo(trainInfoTagRef);

//                                        $("<p/>").html(trainRank[i]+" Circle Line train in " + p.trains[i].timeTo + " min").appendTo(trainInfoTagRef);
                            }
                        }
                    }
                })
                $('html, body').animate({ scrollTop: 0 }, 'fast');
            }
        ).error(function(data){
                $(trainInfoTagRef).empty();
                $("<h3/>").html("The service is unavailable").appendTo(trainInfoTagRef);
                $('#left-list').empty()
                $('#right-list').empty()
                $('<h4/>').html('<i>Oops... we are down sorry!</i>').appendTo('#loading-msg')

            }
        )

        trainTimeout = setTimeout(selectedTrain,30000,selectedTrainID)

    }

    initStationsList();

};

