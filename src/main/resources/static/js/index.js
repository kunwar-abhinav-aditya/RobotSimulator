    var payload = "";

    $(document).ready(function() {
        $("#UI").hide();

        $("#startbtn").click(function(){
            $("#UI").show();
            $("#startbtndiv").hide();
            getNewBoard();
        });

        $("#exBtn1").click(function(){
            resetEverything();
            $("#robotStepsInput").val("POSITION 2 2 WEST\nFORWARD 2\nRIGHT\nFORWARD 1");
        });
        $("#exBtn2").click(function(){
            resetEverything();
            $("#robotStepsInput").val("FORWARD 1\nLEFT\nFORWARD 2\nTURNAROUND");
        });
        $("#exBtn3").click(function(){
            resetEverything();
            $("#robotStepsInput").val("RIGHT\nFORWARD 4\nLEFT\nFORWARD 4\nLEFT");
        });
        $("#submitMoves").click(function(){
            cleanBoard();
            makeMoves();
        });
    });

    function getNewBoard() {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/robotgame?boardSize=5',
            dataType: "json",
            success: function(robotJson) {
                $("#00").append("<i class='fa fa-caret-right' style='font-size:45px;'></i>");
                $("#robotStateOutput").val(robotJson['xposition'] + " " + robotJson['yposition'] + " " +robotJson['currentDirection']);
            },
            error: function(error) {
                console.log("Some error occured");
            }
        });
    }

    function makeMoves() {
        payload = $("#robotStepsInput").val();
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/robotgame/makeMovesAndGetNewPosition',
            dataType: "json",
            contentType: "text/html; charset=UTF-8",
            data: payload,
            success: function(robotJson) {
                let htmlToAppend = '';
                if(robotJson['currentDirection'] == "EAST") {
                    htmlToAppend = "<i class='fa fa-caret-right' style='font-size:45px;'></i>";
                } else if(robotJson['currentDirection'] == "WEST") {
                    htmlToAppend = "<i class='fa fa-caret-left' style='font-size:45px;'></i>"
                } else if(robotJson['currentDirection'] == "NORTH") {
                    htmlToAppend = "<i class='fa fa-caret-up' style='font-size:45px;'></i>"
                } else {
                    htmlToAppend = "<i class='fa fa-caret-down' style='font-size:45px;'></i>"
                }
                $("#"+robotJson['xposition']+""+robotJson['yposition']).append(htmlToAppend);
                $("#robotStateOutput").val(robotJson['xposition'] + " " + robotJson['yposition'] + " " +robotJson['currentDirection']);
            },
            error: function(error) {
                console.log("Some error occured");
            }
        });
    }

    function cleanBoard() {
        for (let i=0; i<5; i++) {
            for (let j=0; j<5; j++) {
                $("#"+i+""+j).empty();
            }
        }
    }

    function resetEverything() {
        cleanBoard();
        getNewBoard();
    }
