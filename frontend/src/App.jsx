import { useState } from 'react';
import './styles.css';

const stats = [
  { label: 'Workout days', value: '7' },
  { label: 'Meals tracked', value: '30+' },
  { label: 'Success rate', value: '92%' },
];

const highlights = [
  {
    title: 'Personalized plan',
    text: 'Follow structured programs suited for your body goals, recovery needs, and fitness level.',
  },
  {
    title: 'Nutrition guidance',
    text: 'Get balanced meal suggestions and calorie targets to support your daily training.',
  },
  {
    title: 'Progress tracking',
    text: 'Measure milestones, monitor consistency, and keep your routine sustainable.',
  },
];

const weeklyPlan = [
  { day: 'Monday', focus: 'Upper body' },
  { day: 'Tuesday', focus: 'Cardio' },
  { day: 'Wednesday', focus: 'Lower body' },
  { day: 'Thursday', focus: 'Core' },
  { day: 'Friday', focus: 'Strength' },
  { day: 'Saturday', focus: 'Recovery' },
];

export default function App() {
  const [authMode, setAuthMode] = useState(null);
  const [authMessage, setAuthMessage] = useState('');

  function openAuth(mode) {
    setAuthMessage('');
    setAuthMode(mode);
  }

  function submitAuth(event) {
    event.preventDefault();
    setAuthMessage('The form is ready, but account access is not connected to the server yet.');
  }

  return (
    <div className="page-shell">
      <header className="topbar">
        <div className="brand">Health<span>Fitness</span></div>
        <nav className="nav">
          <a href="#features">Features</a>
          <a href="#programs">Programs</a>
          <a href="#about">About</a>
          <button className="nav-button" type="button" onClick={() => openAuth('login')}>Login</button>
        </nav>
      </header>

      <main className="hero">
        <section className="hero-copy">
          <span className="eyebrow">Smart Wellness</span>
          <h1>
            Train smarter.<br />
            Eat better.<br />
            Live stronger.
          </h1>
          <p>
            Build a healthier lifestyle with guided workouts, nutrition plans, and real progress tracking in one place.
          </p>

          <div className="cta-row">
            <button className="primary-btn" type="button" onClick={() => openAuth('register')}>Start now</button>
            <a className="secondary-btn" href="#programs">View plans</a>
          </div>

          <div className="stats-grid">
            {stats.map((item) => (
              <div key={item.label} className="stat-card">
                <strong>{item.value}</strong>
                <span>{item.label}</span>
              </div>
            ))}
          </div>
        </section>

        <aside className="hero-panel">
          <div className="panel-card">
            <div className="panel-header">
              <span>Today</span>
              <span className="status-pill">On track</span>
            </div>

            <ul className="mini-list">
              <li><span>Workout</span><b>Push day</b></li>
              <li><span>Cardio</span><b>20 min</b></li>
              <li><span>Nutrition</span><b>High protein</b></li>
            </ul>
          </div>

          <div className="panel-card inverse">
            <p className="card-label">Recommended routine</p>
            <h3>Strength + conditioning</h3>
            <div className="progress">
              <span style={{ width: '72%' }} />
            </div>
            <small>72% weekly goal</small>
          </div>
        </aside>
      </main>

      <section id="features" className="feature-section">
        <div className="section-heading">
          <span className="eyebrow">Why choose us</span>
          <h2>Everything your fitness journey needs</h2>
        </div>

        <div className="feature-grid">
          {highlights.map((item) => (
            <article key={item.title} className="feature-card">
              <div className="icon">✦</div>
              <h3>{item.title}</h3>
              <p>{item.text}</p>
            </article>
          ))}
        </div>
      </section>

      <section id="programs" className="program-section">
        <div className="section-heading left">
          <span className="eyebrow">Weekly plan</span>
          <h2>Train with intent</h2>
        </div>

        <div className="plan-grid">
          {weeklyPlan.map((item) => (
            <div key={item.day} className="day-card">
              <span>{item.day}</span>
              <strong>{item.focus}</strong>
            </div>
          ))}
        </div>
      </section>

      <section id="about" className="cta-banner">
        <div>
          <span className="eyebrow">Ready to begin?</span>
          <h2>Take the next step toward a stronger routine.</h2>
        </div>
        <button className="primary-btn" type="button" onClick={() => openAuth('register')}>Join now</button>
      </section>

      {authMode && (
        <div
          className="auth-overlay"
          onClick={(event) => {
            if (event.target === event.currentTarget) setAuthMode(null);
          }}
          onKeyDown={(event) => {
            if (event.key === 'Escape') setAuthMode(null);
          }}
        >
          <section className="auth-dialog" role="dialog" aria-modal="true" aria-labelledby="auth-title">
            <button className="auth-close" type="button" aria-label="Close" onClick={() => setAuthMode(null)}>×</button>
            <span className="eyebrow">HealthFitness account</span>
            <h2 id="auth-title">{authMode === 'login' ? 'Welcome back' : 'Create your account'}</h2>
            <p className="auth-intro">
              {authMode === 'login' ? 'Sign in to continue your fitness journey.' : 'Start building a routine that works for you.'}
            </p>

            <div className="auth-tabs" role="tablist" aria-label="Account access">
              <button type="button" role="tab" aria-selected={authMode === 'login'} className={authMode === 'login' ? 'active' : ''} onClick={() => openAuth('login')}>Login</button>
              <button type="button" role="tab" aria-selected={authMode === 'register'} className={authMode === 'register' ? 'active' : ''} onClick={() => openAuth('register')}>Register</button>
            </div>

            <form className="auth-form" onSubmit={submitAuth}>
              {authMode === 'register' && (
                <>
                  <label htmlFor="fullName">Full name</label>
                  <input id="fullName" name="fullName" type="text" autoComplete="name" placeholder="Your name" required />
                  <label htmlFor="phone">Phone number</label>
                  <input id="phone" name="phone" type="tel" autoComplete="tel" placeholder="Your phone number" required />
                </>
              )}
              <label htmlFor="email">Email address</label>
              <input id="email" name="email" type="email" autoComplete="email" placeholder="you@example.com" required />
              <label htmlFor="password">Password</label>
              <input id="password" name="password" type="password" autoComplete={authMode === 'login' ? 'current-password' : 'new-password'} minLength={8} placeholder="At least 8 characters" required />
              <button className="primary-btn auth-submit" type="submit">{authMode === 'login' ? 'Sign in' : 'Create account'}</button>
              {authMessage && <p className="auth-message" role="status">{authMessage}</p>}
            </form>
          </section>
        </div>
      )}
    </div>
  );
}
