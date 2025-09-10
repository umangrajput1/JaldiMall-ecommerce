import React from 'react';

function ShopByCategoryCards() {
    return (
    <div className="flex flex-col items-center">
        <div
        className="
            group
            w-[120px] h-[120px] lg:w-[200px] lg:h-[200px]
            rounded-full
            border-[7px] border-pink-500
            overflow-hidden
            cursor-pointer
            transition-colors duration-700
            hover:border-green-500
            flex items-center justify-center
        "
        >
        <img
            src="https://rukminim2.flixcart.com/image/612/612/xif0q/t-shirt/k/n/0/s-laker23yel-fashion-fusion-original-imahfhvyqbfacgfb.jpeg?q=70"
            alt="Sports & Active Wear"
            className="
            w-full h-full object-cover object-top
            group-hover:scale-95
            transition-transform duration-700 ease-in-out
          "
        />
      </div>

    
      <h1 className="mt-2 text-center text-xs lg:text-sm font-medium">
        Sports & Active Wear
      </h1>
    </div>
  );
}

export default ShopByCategoryCards;
