from flask import Flask, render_template, send_from_directory

# Server Networking: In your chosen server language write a web server for at least one
# piece of functionality for your app allowing users to interact with each other. This will include:
#     1. At least 1 API endpoint to which a user - or rather, their browser - will connect to
# while using this feature. Realistically you might need 2 or more endpoints though
# 2. A database or file to store the interactions
# 3. A way to test this functionality
# This does not have to be connected to a front end and testing can be done with a Python
# program that sends requests to the server directly (or any other way you would like to show
# your testing during the demo).
# An example would be a way for players to join the server with endpoints:
#     ● POST “/join” with the body containing a username which adds the player to the game
# ● POST “/leave” with the body containing a username which removes the player from
# the game
# ● GET “/players” returning a list of all the players currently in the game
# You could then write a script that adds 5 players, removes 2 of them, then go to your
# browser to request “/players” and verify the player list

# IN PROGRESS. Not final version yet

app = Flask(__name__)


@app.route('/')
def any_namea():
    helper = send_from_directory('src', 'index.html')
    return render_template(helper)


@app.route('/Menu.js')
def any_nameb():
    return send_from_directory('view', 'Menu.js')


# @app.route('/tickets')
# def get_tickets():
# # return tickets.get_ticket_data('https://data.cityofnewyork.us/resource/2pc8-n4xe.json')


app.run(host='0.0.0.0', port=8080, debug=True)