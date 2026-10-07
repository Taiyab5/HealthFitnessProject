<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TAIYAB Fitness - Member Login</title>
    <style>
        /* --- Professor's CSS Reset & Base --- */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Montserrat', 'Segoe UI', Tahoma, Geneva, sans-serif;
        }

        body {
            /* Gym poster background with a dark contrast gradient */
            background: linear-gradient(rgba(10, 10, 10, 0.65), rgba(10, 10, 10, 0.85)), 
                        url('https://img.freepik.com/premium-psd/gym-poster-design-template_444361-1350.jpg?w=2000') no-repeat center center;
            background-size: cover;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
            position: relative;
        }

        /* --- Dynamic VFX Canvas Layer --- */
        #vfx-canvas {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 1; /* Sits over the background, behind the login box */
            pointer-events: none; /* Allows clicks to pass right through */
        }

        /* --- Premium Glassmorphism Login Box --- */
        .login-box {
            background: rgba(15, 15, 15, 0.75);
            padding: 45px 35px;
            border-radius: 12px;
            width: 100%;
            max-width: 420px;
            box-shadow: 0 20px 50px rgba(0, 0, 0, 0.7); 
            border: 1px solid rgba(255, 255, 255, 0.08);
            backdrop-filter: blur(15px);
            -webkit-backdrop-filter: blur(15px);
            z-index: 2; /* Highest layer to ensure complete interactivity */
        }

        /* Typography */
        .login-box h2 {
            color: #ffffff;
            text-align: center;
            font-size: 32px;
            margin-bottom: 30px;
            letter-spacing: 2px;
            text-transform: uppercase;
            font-weight: 800;
        }

        .login-box h2 span {
            color: #e43d12;
        }

        /* Input Fields */
        .input-group {
            margin-bottom: 22px;
            position: relative;
        }

        .input-group label {
            display: block;
            color: #b3b3b3;
            margin-bottom: 8px;
            font-size: 13px;
            text-transform: uppercase;
            letter-spacing: 1px;
            font-weight: 600;
        }

        .input-group input {
            width: 100%;
            padding: 14px 15px;
            background: rgba(0, 0, 0, 0.5);
            border: 1px solid #333;
            border-radius: 6px;
            color: white;
            font-size: 16px;
            outline: none;
            transition: all 0.3s ease;
        }

        .input-group input:focus {
            border-color: #e43d12;
            background: rgba(0, 0, 0, 0.8);
            box-shadow: inset 0 0 5px rgba(228, 61, 18, 0.2);
        }

        /* Password Toggle */
        .toggle-password {
            position: absolute;
            right: 15px;
            top: 40px;
            background: none;
            border: none;
            color: #b3b3b3;
            cursor: pointer;
            font-size: 13px;
            font-weight: bold;
            text-transform: uppercase;
        }
        
        .toggle-password:hover {
            color: #e43d12;
        }

        /* Remember Me & Options */
        .options {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
            font-size: 13px;
        }
        
        .options label {
            color: #b3b3b3;
            display: flex;
            align-items: center;
            gap: 8px;
            cursor: pointer;
        }

        .options a {
            color: #b3b3b3;
            text-decoration: none;
            transition: 0.3s;
        }

        .options a:hover {
            color: #e43d12;
        }

        /* Premium Login Button */
        .btn-login {
            width: 100%;
            padding: 15px;
            background: #e43d12;
            background: linear-gradient(135deg, #e43d12, #c2330f);
            border: none;
            border-radius: 6px;
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        .btn-login:hover {
            background: linear-gradient(135deg, #ff4c1f, #e43d12);
            transform: translateY(-2px);
            box-shadow: 0 10px 20px rgba(228, 61, 18, 0.4);
        }

        /* Error Message */
        .error-msg {
            color: #ff4757;
            background: rgba(255, 71, 87, 0.1);
            padding: 12px;
            border-radius: 6px;
            text-align: center;
            margin-bottom: 20px;
            font-size: 14px;
            border: 1px solid rgba(255, 71, 87, 0.3);
            animation: shake 0.4s ease-in-out;
        }

        @keyframes shake {
            0%, 100% { transform: translateX(0); }
            25% { transform: translateX(-5px); }
            75% { transform: translateX(5px); }
        }

        /* Footer Links */
        .footer-link {
            text-align: center;
            margin-top: 25px;
            color: #b3b3b3;
            font-size: 14px;
        }

        .footer-link a {
            color: #e43d12;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s;
        }

        .footer-link a:hover {
            color: #ff4c1f;
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <!-- Dynamic Neon Ember VFX Canvas Overlay -->
    <canvas id="vfx-canvas"></canvas>

    <div class="login-box">
        <h2>TAIYAB <span>Fitness</span></h2>
        
        <% 
            String errorMsg = request.getParameter("error"); 
            if(errorMsg != null) { 
        %>
            <div class="error-msg">
                ⚠️ Invalid Email or Password.
            </div>
        <% } %>

        <form action="UserController" method="POST">
            <input type="hidden" name="action" value="login">
            
            <div class="input-group">
                <label for="email">Email Address</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" autocomplete="email" required>
            </div>
            
            <div class="input-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" autocomplete="current-password" required>
                <button type="button" class="toggle-password" onclick="togglePwd()">Show</button>
            </div>

            <div class="options">
                <label>
                    <input type="checkbox" name="remember"> Remember me
                </label>
                <a href="forgot-password.jsp">Forgot Password?</a>
            </div>
            
            <button type="submit" class="btn-login">Log In</button>
        </form>

        <div class="footer-link">
            Not a member yet? <a href="register.jsp">Join the club</a>
        </div>
    </div>

    <script>
        // Password Show/Hide Toggle
        function togglePwd() {
            const pwdInput = document.getElementById('password');
            const toggleBtn = document.querySelector('.toggle-password');
            
            if (pwdInput.type === 'password') {
                pwdInput.type = 'text';
                toggleBtn.textContent = 'Hide';
            } else {
                pwdInput.type = 'password';
                toggleBtn.textContent = 'Show';
            }
        }

        // Professor's VFX Particle Engine over the Poster Background
        const canvas = document.getElementById('vfx-canvas');
        const ctx = canvas.getContext('2d');

        function resizeCanvas() {
            canvas.width = window.innerWidth;
            canvas.height = window.innerHeight;
        }
        window.addEventListener('resize', resizeCanvas);
        resizeCanvas();

        const particlesArray = [];
        const numberOfParticles = 55;

        class Particle {
            constructor() {
                this.x = Math.random() * canvas.width;
                this.y = Math.random() * canvas.height;
                this.size = Math.random() * 3 + 1;
                this.speedX = (Math.random() - 0.5) * 1.0;
                this.speedY = -Math.random() * 1.2 - 0.4; // Floating upward smoothly like fiery gym sparks
                this.color = Math.random() > 0.4 ? '#e43d12' : '#ff6b35';
                this.alpha = Math.random() * 0.6 + 0.2;
            }
            update() {
                this.x += this.speedX;
                this.y += this.speedY;
                if (this.y < 0) {
                    this.y = canvas.height + 10;
                    this.x = Math.random() * canvas.width;
                }
            }
            draw() {
                ctx.save();
                ctx.globalAlpha = this.alpha;
                ctx.fillStyle = this.color;
                ctx.beginPath();
                ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
                ctx.fill();
                ctx.restore();
            }
        }

        for (let i = 0; i < numberOfParticles; i++) {
            particlesArray.push(new Particle());
        }

        function animate() {
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            particlesArray.forEach(particle => {
                particle.update();
                particle.draw();
            });
            requestAnimationFrame(animate);
        }
        animate();
    </script>
</body>
</html>