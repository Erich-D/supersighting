/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    getForms();
    getEdits();
});

function getForms(){
    $("#toggleSuperForm").click(function(event){
        $("#addSuper").show();
    });
    $("#heroSubmit").click(function(event){
        $("#addSuper").hide();
    });
    $("#togglePowersForm").click(function(event){
        $("#addPower").show();
    });
    $("#powerSubmit").click(function(event){
        $("#addPower").hide();
    });
    $("#toggleLocalForm").click(function(event){
        $("#addLoc").show();
    });
    $("#localSubmit").click(function(event){
        $("#addLoc").hide();
    });
    $("#toggleOrgForm").click(function(event){
        $("#addOrg").show();
    });
    $("#orgSubmit").click(function(event){
        $("#addOrg").hide();
    });
    $("#toggleSightForm").click(function(event){
        $("#addSight").show();
    });
    $("#sightSubmit").click(function(event){
        $("#addSight").hide();
    });
}

function getEdits(){
    $("#editSuper").click(function(event){
        $("#superEdit").show();
        
    });
    $("#heroEditSubmit").click(function(event){
        $("#superEdit").hide();
    });
}
