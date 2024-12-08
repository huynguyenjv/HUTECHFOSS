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
            <h3>Đăng ký cứu trợ - Dự án hệ thống cứu trợ V-Relief</h3> <p>Dự án V-Relief giúp tạo nên một hệ thống hỗ trợ cứu trợ toàn diện, mang lại trải nghiệm thân thiện và hiệu quả cho người dùng. Hệ thống cung cấp các chức năng cần thiết để quản lý thông tin và tổ chức cứu trợ một cách hiệu quả, dựa trên nền tảng công nghệ hiện đại.Các thành phần giao diện của V-Relief được xây dựng bằng <b>Reactjs</b> </p>
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
