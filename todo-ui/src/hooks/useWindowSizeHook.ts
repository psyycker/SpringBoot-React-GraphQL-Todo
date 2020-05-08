import React, { useEffect, useState } from 'react'

export default function useWindowSize(callback) {

  useEffect(() => {

    window.addEventListener('resize', callback);
    return () => window.removeEventListener('resize', callback);
  }, []); // Empty array ensures that effect is only run on mount and unmount
}
