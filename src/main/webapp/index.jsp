<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HealthFitnessProject</title>
    <style>
        :root {
            --bg-dark: #0d1117;
            --bg-soft: #121a27;
            --card: rgba(18, 26, 39, 0.8);
            --card-border: rgba(255,255,255,0.08);
            --primary: #ff7a18;
            --primary-dark: #e55f00;
            --text: #f5f7fa;
            --muted: #b7c1cf;
            --green: #3ddc97;
        }

        * { box-sizing: border-box; }

        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
            background: linear-gradient(135deg, #0b1320, #111827 40%, #030712);
            color: var(--text);
            min-height: 100vh;
        }

        .container {
            width: min(1100px, 90%);
            margin: 0 auto;
        }

        .topbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 24px 0;
        }

        .brand {
            font-size: 1.7rem;
            font-weight: 700;
            letter-spacing: 1px;
        }

        .brand span {
            color: var(--primary);
        }

        nav {
            display: flex;
            gap: 18px;
            align-items: center;
        }

        nav a {
            color: var(--muted);
            text-decoration: none;
            font-weight: 600;
        }

        nav a:hover {
            color: var(--text);
        }

        .hero {
            display: grid;
            grid-template-columns: 1.2fr 0.8fr;
            gap: 40px;
            padding: 40px 0 60px;
            align-items: center;
        }

        .badge {
            display: inline-block;
            background: rgba(255, 122, 24, 0.12);
            border: 1px solid rgba(255, 122, 24, 0.3);
            color: #ffb380;
            padding: 8px 16px;
            border-radius: 999px;
            font-size: 0.8rem;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin-bottom: 18px;
        }

        h1 {
            font-size: clamp(2.8rem, 5vw, 5rem);
            line-height: 1.05;
            margin: 0 0 18px;
        }

        h1 span {
            color: var(--primary);
        }

        .hero p {
            color: var(--muted);
            font-size: 1.08rem;
            line-height: 1.7;
            margin-bottom: 28px;
            max-width: 600px;
        }

        .cta {
            display: flex;
            gap: 18px;
            margin-bottom: 28px;
            flex-wrap: wrap;
        }

        .btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            padding: 14px 26px;
            border-radius: 10px;
            text-decoration: none;
            font-weight: 700;
            transition: 0.2s ease;
        }

        .btn.primary {
            background: linear-gradient(135deg, var(--primary), var(--primary-dark));
            color: #fff;
            box-shadow: 0 14px 30px rgba(255, 122, 24, 0.35);
        }

        .btn.secondary {
            background: transparent;
            color: var(--text);
            border: 1px solid rgba(255,255,255,0.15);
        }

        .btn:hover {
            transform: translateY(-2px);
        }

        .stats {
            display: flex;
            gap: 24px;
            flex-wrap: wrap;
        }

        .stat {
            background: rgba(255,255,255,0.02);
            border: 1px solid var(--card-border);
            border-radius: 12px;
            padding: 14px 18px;
            min-width: 150px;
        }

        .stat strong {
            display: block;
            font-size: 1.5rem;
            color: var(--green);
            margin-bottom: 6px;
        }

        .stat span {
            color: var(--muted);
            font-size: 0.9rem;
        }

        .card {
            background: var(--card);
            border: 1px solid var(--card-border);
            border-radius: 22px;
            padding: 26px;
            box-shadow: 0 24px 60px rgba(0,0,0,0.3);
        }

        .mini-box {
            background: rgba(255,255,255,0.03);
            border: 1px solid rgba(255,255,255,0.08);
            border-radius: 16px;
            padding: 16px 18px;
            margin-bottom: 18px;
        }

        .mini-box h3 {
            margin: 0 0 12px;
            font-size: 1rem;
            color: var(--muted);
            letter-spacing: 0.08em;
            text-transform: uppercase;
        }

        .workout-list {
            list-style: none;
            padding: 0;
            margin: 0;
            display: grid;
            gap: 12px;
        }

        .workout-list li {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background: rgba(255,255,255,0.02);
            border-radius: 12px;
            padding: 12px 14px;
            color: var(--text);
        }

        .pill {
            background: rgba(61, 220, 151, 0.15);
            color: var(--green);
            border-radius: 999px;
            padding: 6px 10px;
            font-size: 0.75rem;
            font-weight: 700;
        }

        @media (max-width: 860px) {
            .hero {
                grid-template-columns: 1fr;
            }

            .topbar {
                flex-direction: column;
                gap: 14px;
            }

            nav {
                flex-wrap: wrap;
                justify-content: center;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <header class="topbar">
            <div class="brand">Health<span>Fitness</span></div>
            <nav>
                <a href="login.jsp">Login</a>
                <a href="register.jsp">Register</a>
                <a href="dashboard.jsp">Dashboard</a>
            </nav>
        </header>

        <main class="hero">
            <section>
                <div class="badge">Smart wellness tracking</div>
                <h1>Build a stronger <span>body</span> and better habits.</h1>
                <p>
                    Track your daily workouts, nutrition planning, and consistent progress in one simple lifestyle dashboard.
                    Designed for personal growth, routine discipline, and long-term fitness goals.
                </p>

                <div class="cta">
                    <a class="btn primary" href="login.jsp">Login</a>
                    <a class="btn secondary" href="register.jsp">Create Account</a>
                </div>

                <div class="stats">
                    <div class="stat">
                        <strong>7</strong>
                        <span>Workout Days</span>
                    </div>
                    <div class="stat">
                        <strong>Daily</strong>
                        <span>Nutrition Plans</span>
                    </div>
                    <div class="stat">
                        <strong>1</strong>
                        <span>Progress Hub</span>
                    </div>
                </div>
            </section>

            <aside class="card">
                <div class="mini-box">
                    <h3>Today</h3>
                    <ul class="workout-list">
                        <li><span>Push Day</span><span class="pill">Active</span></li>
                        <li><span>Cardio</span><span class="pill">20 min</span></li>
                        <li><span>Meal plan</span><span class="pill">Balanced</span></li>
                    </ul>
                </div>

                <div class="mini-box">
                    <h3>Focus Area</h3>
                    <ul class="workout-list">
                        <li><span>Chest</span><span>Bench Press</span></li>
                        <li><span>Legs</span><span>Squats</span></li>
                        <li><span>Core</span><span>Planks</span></li>
                    </ul>
                </div>
            </aside>
        </main>
    </div>
</body>
</html>
