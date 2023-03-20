# vehiclegarage
This document gives a rundown of the endpoints present in the project.
# Endpoints

## Create Vehicle
<p><strong>Description:</strong> Creates a vehicle with given input values.</p><br>
<strong>Endpoint:</strong> <code>POST v1/vehicles</code><br>
<strong> Payload </strong>
<pre><code>{
    "type": "string",
    "plateNo": "string",
    "color": "string",
}
</code></pre>

<strong> Response(s) </strong>
<li>HTTP Status Code 200 and <code>Response</code> object</li>
<strong> Note(s)</strong>
<li><code>plateNo</code> and <code>color</code> fields are nullable. Only <code> type </code> has to be present in order to produce the 200 response.</li>

## Park Vehicle
<p><strong>Description:</strong> Parks a vehicle with given input values.</p><br>
<strong>Endpoint:</strong> <code>POST v1/vehicles/park</code><br>
<strong> Payload </strong>
<pre><code>{
    "park": "string"
}
</code></pre>

<strong> Response(s) </strong>
<li>HTTP Status Code 200 and <code>ParkResponse</code> object</li>
<strong> Note(s)</strong>
<li>The input should follow the given order: <code>dash-separated-plateNo</code> <code>color</code> <code>type</code>.</li>
<li>If the car is not present in the database, then it will automatically get added.</li>

## Leave Vehicle
<p><strong>Description:</strong> Leaves a vehicle with given parking index.</p><br>
<strong>Endpoint:</strong> <code>POST v1/vehicles/leave</code><br>
<strong> Payload </strong>
<pre><code>{
    "leave": "int"
}
</code></pre>

<strong> Response(s) </strong>
<li>HTTP Status Code 200 and <code>LeaveResponse</code> object</li>
<strong> Note(s)</strong>
<li>Parking index is based on the order of parked cars. It will not update once a car leaves the garage.</li>
<li>For instance: If 3 cars park in order and the second one leaves then "leave: 3" should be called for the last car to leave.</li>

## Get Garage Status
<p><strong>Description:</strong> Displays the current cars in the garage.</p><br>
<strong>Endpoint:</strong> <code>GET v1/vehicles/status</code><br>
<strong> Response(s) </strong>
<li>HTTP Status Code 200 and <code>StatusResponse</code> object</li>
<strong> Note(s)</strong>
<li>Display shows the empty spots aswell. If a car were to leave, the remaining cars in the garage will remain in their previous spots.</li>

<h1 id="objects">Objects</h1>
<p>This section provides the content of the objects used for front-end communication.</p>

<h2 id="parkresponse"><code>ParkResponse</code></h2>
<pre><code>{
 "message": "string"
 "created": {
   "plateNo": "string",
   "color": "string",
   "type": "string",
   "size": "string"
 }
 "parked": {
   "plateNo": "string",
   "color": "string",
   "type": "string",
   "size": "string"
 }
}
</code></pre>
<h2 id="leaveresponse"><code>LeaveResponse</code></h2>
<pre><code>{
 "message": "string"
 "left": {
   "plateNo": "string",
   "color": "string",
   "type": "string",
   "size": "string"
 }
}
</code></pre>
<h2 id="statusresponse"><code>StatusResponse</code></h2>
<pre><code>{
 "vehicles": "string"
}
</code></pre>
