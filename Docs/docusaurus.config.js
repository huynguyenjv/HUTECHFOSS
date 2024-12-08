// @ts-check
// `@type` JSDoc annotations allow editor autocompletion and type checking
// (when paired with `@ts-check`).
// There are various equivalent ways to declare your Docusaurus config.
// See: https://docusaurus.io/docs/api/docusaurus-config

import {themes as prismThemes} from 'prism-react-renderer';

// This runs in Node.js - Don't use client-side code here (browser APIs, JSX...)

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'V-RELIEF',
  tagline: 'Nền tảng hỗ trợ nhanh chóng cho cộng đồng trong và sau thiên tai.',
  favicon: 'img/V-RELIEF.ico',

  // Set the production url of your site here
  url: 'https://hutechfoss.netlify.app/',
  // Set the /<baseUrl>/ pathname under which your site is served
  // For GitHub pages deployment, it is often '/<projectName>/'
  baseUrl: '/',

  // GitHub pages deployment config.
  // If you aren't using GitHub pages, you don't need these.
  organizationName: 'facebook', // Usually your GitHub org/user name.
  projectName: 'docusaurus', // Usually your repo name.

  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',

  // Even if you don't use internationalization, you can use this field to set
  // useful metadata like html lang. For example, if your site is Chinese, you
  // may want to replace "en" with "zh-Hans".
  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },
  
  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: './sidebars.js',
          // Please change this to your repo.
          // Remove this to remove the "edit this page" links.
          editUrl:
            'https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/',
        },
        blog: {
          showReadingTime: true,
          feedOptions: {
            type: ['rss', 'atom'],
            xslt: true,
          },
          // Please change this to your repo.
          // Remove this to remove the "edit this page" links.
          editUrl:
            'https://github.com/facebook/docusaurus/tree/main/packages/create-docusaurus/templates/shared/',
          // Useful options to enforce blogging best practices
          onInlineTags: 'warn',
          onInlineAuthors: 'warn',
          onUntruncatedBlogPosts: 'warn',
        },
        theme: {
          customCss: './src/css/custom.css',
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      // Replace with your project's social card
      image: 'img/banner.png',
      navbar: {
        title: 'V-RELIEF',
        logo: {
          alt: 'V-RELIEF Logo',
          href: 'http://localhost:3000/',
          target: '_self',
          width: 35,
          height: 40,
          src: 'img/V-RELIEF.ico',
        },
        hideOnScroll: true,
        items: [
          {
            type: 'docSidebar',
            sidebarId: 'tutorialSidebar',
            position: 'left',
            label: 'Documentation',
          },
          {
            label: 'Website',
            href: 'https://www.hutech.edu.vn/',
          },
          {
            href: 'https://github.com/facebook/docusaurus',
            position: 'right',
            label: 'GitHub'
          },
        ],
      },
      colorMode: {
        defaultMode: 'light', // hoặc 'dark', tùy vào chế độ mặc định bạn muốn
        disableSwitch: true,  // Tắt nút chuyển đổi
        respectPrefersColorScheme: false, // Bỏ qua chế độ màu của hệ điều hành người dùng
      },
      footer: {
        style: 'dark',
        links: [
          {
            title: 'Organization',
            items: [
              {
                label: 'VAIP',
                href: 'http://vaip.org.vn/',
              },
              {
                label: 'VFOSSA',
                href: 'https://vfossa.vn/',
              },
              {
                label: 'HANOI UNIVERSITY OF INDUSTRY',
                href: 'https://www.haui.edu.vn/',
              },
            ],
          },
          {
            title: 'Community',
            items: [
              {
                label: 'Stack Overflow',
                href: 'https://stackoverflow.com/questions/tagged/docusaurus',
              },
              {
                label: 'Discord',
                href: 'https://discordapp.com/invite/docusaurus',
              },
              {
                label: 'X',
                href: 'https://x.com/docusaurus',
              },
            ],
          },
          {
            title: 'More',
            items: [
              {
                label: 'Blog',
                to: '/blog',
              },
              {
                label: 'GitHub',
                href: 'https://github.com/facebook/docusaurus',
              },
            ],
          },
          {
            title: 'More',
            items: [
              {
                label: 'Blog',
                to: '/blog',
              },
              {
                label: 'GitHub',
                href: 'https://github.com/facebook/docusaurus',
              },
            ],
          },
        ],
        logo: {
          alt: 'Hutech Logo',
          href: 'https://www.hutech.edu.vn/',
          target: '_blank',
          width: 320,
          height: 100,
          src: 'img/logo-hutech.png',
        },
        copyright: `Copyright © ${new Date().getFullYear()} Hutech. All rights reserved`,
      },
    
    }),
};

export default config;
