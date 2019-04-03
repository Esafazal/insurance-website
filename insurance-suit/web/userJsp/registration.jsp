<%-- 
    Document   : registration
    Created on : Mar 21, 2019, 6:18:01 PM
    Author     : crazydude
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Registration</title>
        <link href="../css/register.css" rel="stylesheet">
    </head>
    <body>

        <form action="Register" method="post" style="border:1px solid #ccc">
            <div class="container">
                <h1>Sign Up</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>

                <label for="first name"><b>First Name</b></label>
                <input type="text" placeholder="Enter First Name" name="firstname" required>

                <label for="last name"><b>Last Name</b></label>
                <input type="text" placeholder="Enter Last Name" name="lastname" required>

                <label for="address"><b>Address</b></label>
                <input type="text" placeholder="Enter Address" name="address" required>

                <label for="dob"><b>DOB</b></label>
                <input type="date" placeholder="Enter Date of Birth" name="dob" required>

                <label for="nic"><b>NIC</b></label>
                <input type="text" placeholder="Enter NIC" maxlength="10"  name="nic" required>

                <label for="email"><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" required>

                <label for="phone no"><b>Phone No</b></label>
                <input type="tel" placeholder="Enter Phone number" maxlength="10" minlength="10" name="phoneno" required>

                <hr><h3><b>Register Vehicle</b></h3><hr>

                <label for="vehicle type"><b>Vehicle Type</b></label>
                <select name="vehicle_type">
                    <option value="car">Car</option>
                    <option value="van">Van</option>
                    <option value="bike">motorcycle</option>
                    <option value="three wheeler">Three Wheeler</option>
                </select>

                <label for="vehicle number"><b>Vehicle Number</b></label>
                <input type="text" placeholder="Ex: ABC-1234" maxlength="8" minlength="7" name="vehicle_number" required>

                <label for="vehcle model"><b>Vehicle Model</b></label>
                <input type="text" placeholder="Ex: Mercedes S class" name="vehicle_model" required>

                <label for="vehicle condition"><b>Vehicle Condition</b></label>
                <textarea type="text" placeholder="Enter Vehicle Condition" name="vehicle_condition" required></textarea>
                <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

                <div class="clearfix">
                    <button type="button" class="cancelbtn" onclick="window.location.href = '../index.jsp'">Cancel</button>
                    <button type="submit" class="signupbtn" >Register</button>
                </div>
            </div>
        </form>

    </body>
</html>
