import React from 'react'

function AdsBanner() {
  return (
        <div className="pb-20">
            <section
                className="
                relative 
                h-[200px] md:h-[300px] lg:h-[450px] 
                mb-5 lg:mb-10 
                lg:px-20
                "
            >
                {/* Background image */}
                <img
                className="w-full h-full object-cover rounded-lg"
                src="https://static.vecteezy.com/system/resources/previews/006/642/998/non_2x/online-shopping-on-website-e-commerce-applications-and-digital-marketing-hand-holding-smartphonwith-the-delivery-man-template-for-banner-web-landing-page-social-media-flat-design-concept-vector.jpg"
                alt="Banner"
                />

                {/* Text overlay */}
            <div
                className="
                    absolute bottom-6 left-6 sm:left-10 md:left-16 lg:left-24 
                "
                >
                <h1
                    className="
                    text-black 
                    text-[12px] sm:text-[14px] md:text-[18px] lg:text-[22px] 
                    font-bold leading-tight
                    "
                >
                    Sell your Product <br />
                    <span className="text-pink-600">with JaldiMall</span>
                </h1>
            </div>
        </section>
    </div>

  )
}

export default AdsBanner