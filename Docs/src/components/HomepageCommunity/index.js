import React from 'react';
import styles from './styles.module.css';

export default function HomepageCommunity() {
  return (
    <section className={styles.communitySection}>
      <h2 className={styles.communityTitle}>Join our developer community</h2>
      <p className={styles.communityDescription}>
        Open-source is in the <span className={styles.heartEmoji}>💖</span> of Hutech.
        Follow us <span className={styles.starEmoji}>⭐</span> on GitHub, and join our developer security community
        <span className={styles.telegramEmoji}>👾</span> on Discord!
      </p>
      <div className={styles.buttonContainer}>
        <a href="https://github.com/" target="_blank" rel="noopener noreferrer" className={styles.githubButton}>
          Star on GitHub
        </a>
        <a href="https://discord.com/" target="_blank" rel="noopener noreferrer" className={styles.discordButton}>
          Join Discord
        </a>
      </div>
    </section>
  );
}
