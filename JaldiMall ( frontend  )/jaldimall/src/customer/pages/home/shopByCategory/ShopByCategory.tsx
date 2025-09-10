import React from 'react';
import ShopByCategoryCards from './ShopByCategoryCards';

function ShopByCategory() {
  return (
    <div className='flex flex-wrap justify-center gap-2 lg:gap-10 px-5 lg:px-16 py-10 bg-white shadow-md'>
      { [1,1,1,1,1,1,1,1,1,1,1,1].map((item)=><ShopByCategoryCards />) }
    </div>
  );
}

export default ShopByCategory;
