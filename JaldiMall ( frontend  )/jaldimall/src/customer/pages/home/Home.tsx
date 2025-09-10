import React from 'react'
import ElectricCategory from './ElectricCategory/ElectricCategory'
import CategoryGrid from './CategoryGrid/CategoryGrid'
import Deal from './DealGrid/Deal'
import ShopByCategory from './shopByCategory/ShopByCategory'
import AdsBanner from './Banner/AdsBanner'
import Footer from '../../component/Footer/Footer'
function Home() {
    return (
        <>
            <div className='space-y-5 lg:space-y-10 relative'>
                <ElectricCategory />
                <CategoryGrid />

                <section className='pt-13 '>
                    <h1 className='text-lg lg:text-4xl font-bold text-primary-color pb-5
                    lg:pb-8 text-center'>TODAY'S DEAL</h1>
                    <Deal />
                </section>
                
                <section className='pt-13 '>
                    <h1 className='text-lg lg:text-4xl font-bold text-primary-color pb-5
                    lg:pb-1 text-center'>SHOP BY CATEGORY</h1>
                    <ShopByCategory />
                </section>

                <section>
                    <AdsBanner />
                </section>

                <section>
                    <Footer/>
                </section>
        </div>
    </>
    )
}

export default Home