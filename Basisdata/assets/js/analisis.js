(() => {
    const slides = document.querySelectorAll('.slide');
    const totalSlides = slides.length;
    let currentSlide = 1;

    const defaultConfig = {
        presentation_title: 'Analisis Regresi Linear Sederhana',
        subtitle: 'Hubungan Kebahagiaan dan Pendapatan',
        author_name: 'Presentasi Statistik'
    };

    const currentSlideElement = document.getElementById('current-slide');
    const totalSlidesElement = document.getElementById('total-slides');
    const prevButton = document.getElementById('prev-btn');
    const nextButton = document.getElementById('next-btn');

    function updateSlideCounter() {
        if (currentSlideElement) {
            currentSlideElement.textContent = currentSlide;
        }
        if (totalSlidesElement) {
            totalSlidesElement.textContent = totalSlides;
        }
        if (prevButton) {
            prevButton.disabled = currentSlide === 1;
        }
        if (nextButton) {
            nextButton.disabled = currentSlide === totalSlides;
        }
    }

    function scrollToSlide(slideNumber) {
        if (slideNumber >= 1 && slideNumber <= totalSlides) {
            slides[slideNumber - 1].scrollIntoView({
                behavior: 'smooth',
                block: 'start'
            });
            currentSlide = slideNumber;
            updateSlideCounter();
        }
    }

    window.nextSlide = () => {
        if (currentSlide < totalSlides) {
            scrollToSlide(currentSlide + 1);
        }
    };

    window.previousSlide = () => {
        if (currentSlide > 1) {
            scrollToSlide(currentSlide - 1);
        }
    };

    document.addEventListener('keydown', (event) => {
        if (event.key === 'ArrowRight' || event.key === ' ') {
            event.preventDefault();
            window.nextSlide();
        } else if (event.key === 'ArrowLeft') {
            event.preventDefault();
            window.previousSlide();
        }
    });

    updateSlideCounter();

    async function onConfigChange(config = {}) {
        const titleElement = document.getElementById('presentation-title');
        const subtitleElement = document.getElementById('subtitle');
        const authorElement = document.getElementById('author-name');

        if (titleElement) {
            titleElement.textContent = config.presentation_title || defaultConfig.presentation_title;
        }
        if (subtitleElement) {
            subtitleElement.textContent = config.subtitle || defaultConfig.subtitle;
        }
        if (authorElement) {
            authorElement.textContent = config.author_name || defaultConfig.author_name;
        }
    }

    function mapToCapabilities() {
        return {
            recolorables: [],
            borderables: [],
            fontEditable: undefined,
            fontSizeable: undefined
        };
    }

    function mapToEditPanelValues(config = {}) {
        return new Map([
            ['presentation_title', config.presentation_title || defaultConfig.presentation_title],
            ['subtitle', config.subtitle || defaultConfig.subtitle],
            ['author_name', config.author_name || defaultConfig.author_name]
        ]);
    }

    if (window.elementSdk) {
        window.elementSdk.init({
            defaultConfig,
            onConfigChange,
            mapToCapabilities,
            mapToEditPanelValues
        });
    }
})();
