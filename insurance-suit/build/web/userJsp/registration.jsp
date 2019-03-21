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

        <form action="/action_page.php" style="border:1px solid #ccc">
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
                <input type="date" placeholder="Enter Date of Birth" name="dob" required><br><br>

                <label for="nic"><b>NIC</b></label>
                <input type="text" placeholder="Enter NIC" name="nic" required>

                <label for="email"><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" required>

                <label for="phone no"><b>Phone No</b></label>
                <input type="tel" placeholder="Enter Phone number" name="phoneno" required>

                <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

                <div class="clearfix">
                    <button type="button" class="cancelbtn">Cancel</button>
                    <button type="submit" class="signupbtn">Register</button>
                </div>
            </div>
        </form>

    </body>
</html>
