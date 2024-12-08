import styles from './styles.module.css';
import clsx from 'clsx';

export default function HomepageContent() {
  return (
    <main className={styles.content}>
        <section className={styles.description}>
          <div className={clsx("row", styles.descriptionRow)}>
            <div className={clsx("col col--6", styles.imgColumn)}>
              <img src="img/tinhnguyenvien.png" className={styles.demoImg} />
            </div>
            <div className="col col--6">
              <h3>Create native apps for Windows, MacOs and Linux using Vue and CSS</h3>
              <p>lets you create truly native apps and doesn't compromise on your users' experience. It provides a core set of platform agnostic native widgets that map directly to the platform’s native UI building blocks.</p>
              <p>Vue NodeGui widgets are built on top of <a href="https://www.qt.io/" target="_blank">Qt</a> which is a mature desktop apps framework. Vue NodeGui components are extremely customizable just like in the web but does <strong>NOT</strong> use a Web browser under the hood.</p>
            </div>
          </div>
        </section>
        <section className={styles.description}>
          <div className={clsx("row", styles.descriptionRow)}>
            <div className="col col--6">
              <h3>Written in Vue — rendered with native code by Qt</h3>
              <p>Apps can be built completely in JavaScript. This enables native app development for whole new teams of developers, and can let existing native teams work much faster.</p>
              <p>With NodeGui you get flexibility of web and performance of Native desktop apps.</p>
            </div>
            <div className={clsx("col col--6", styles.imgColumn)}>
              <img src="img/nhancuutro.png" className={styles.demoImg} />
            </div>
          </div>
        </section>
        <section className={styles.description}>
          <div className={clsx("row", styles.descriptionRow)}>
          <div className={clsx("col col--6", styles.imgColumn)}>
              <img src="img/mapweather2.png" className={styles.demoImg} />
            </div>
            <div className="col col--6">
              <h3>Written in Vue — rendered with native code by Qt</h3>
              <p>Apps can be built completely in JavaScript. This enables native app development for whole new teams of developers, and can let existing native teams work much faster.</p>
              <p>With NodeGui you get flexibility of web and performance of Native desktop apps.</p>
            </div>
          </div>
        </section>
        <section className={styles.description}>
          <div className={clsx("row", styles.descriptionRow)}>
            <div className="col col--6">
              <h3>Written in Vue — rendered with native code by Qt</h3>
              <p>Apps can be built completely in JavaScript. This enables native app development for whole new teams of developers, and can let existing native teams work much faster.</p>
              <p>With NodeGui you get flexibility of web and performance of Native desktop apps.</p>
            </div>
            <div className={clsx("col col--6", styles.imgColumn)}>
              <img src="img/cuuho.png" className={styles.demoImg} />
            </div>
          </div>
        </section>
      </main>
  );
}
